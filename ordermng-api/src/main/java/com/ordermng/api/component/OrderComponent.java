package com.ordermng.api.component;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ordermng.core.dto.OrderDTO;
import com.ordermng.core.order.OrderBusiness;
import com.ordermng.core.user.UserBusiness;
import com.ordermng.db.order.OrderEntity;
import com.ordermng.db.order.OrderRepository;

@Component
public class OrderComponent implements OrderBusiness {
    private final OrderRepository orderRepository;
    private final UserBusiness userBusiness;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public OrderComponent(OrderRepository orderRepository, UserBusiness userBusiness) {
        this.orderRepository = orderRepository;
        this.userBusiness = userBusiness;
    }

    /**
     * Get the list of active items.
     * @return A list of active items.
     */
    public Iterable<OrderEntity> findAllActive() {
        return orderRepository.findAllActive();
    }

    public Optional<OrderEntity> findActiveById(Long id) {
        return orderRepository.findActiveById(id);
    }

    public OrderEntity save(OrderDTO order) {
        return orderRepository.save(modelMapper.map(order, OrderEntity.class));
    }

    public OrderEntity save(OrderEntity order) {
        return orderRepository.save(order);
    }

    /**
     * This method retrive order from repository based in the src id an try to update the order.
     * @param src The order used to update.
     * @return Return the target of update, the order retrived from repository.
     */
    public Optional<OrderDTO> updateEntity(OrderDTO src) {
        OrderDTO tar = null;
        Optional<OrderEntity> optionalOrder = this.findActiveById(src.getId());

        if(optionalOrder.isPresent()) {
            tar = modelMapper.map(optionalOrder.get(), OrderDTO.class);

            if(!this.isValid(tar, userBusiness)) {
                return Optional.empty();
            }

            this.updateEntity(tar, src);
            this.save(tar);

            return Optional.of(tar);
        }

        return Optional.empty();
    }
}
