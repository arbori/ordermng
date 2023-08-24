package com.ordermng.api.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ordermng.api.model.OrderRequest;
import com.ordermng.core.OrderManagerException;
import com.ordermng.core.dto.OrderDTO;
import com.ordermng.core.dto.OrderItemDTO;
import com.ordermng.core.dto.StockMovementDTO;
import com.ordermng.core.dto.UserDTO;
import com.ordermng.core.order.OrderBusiness;
import com.ordermng.core.user.UserBusiness;
import com.ordermng.db.item.ItemEntity;
import com.ordermng.db.movement.StockMovementEntity;
import com.ordermng.db.movement.StockMovementRepository;
import com.ordermng.db.order.OrderEntity;
import com.ordermng.db.order.OrderItemEntity;
import com.ordermng.db.order.OrderRepository;
import com.ordermng.db.user.UserEntity;

@Component
public class OrderComponent extends OrderBusiness {
    private final OrderRepository orderRepository;
    private final OrderItemComponent orderItemComponent;
    private final StockMovementRepository stockMovementRepository; 
    private final UserBusiness userBusiness;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public OrderComponent(OrderRepository orderRepository, OrderItemComponent orderItemComponent, StockMovementRepository stockMovementRepository, UserBusiness userBusiness) {
        this.orderRepository = orderRepository;
        this.orderItemComponent = orderItemComponent;
        this.stockMovementRepository = stockMovementRepository;
        this.userBusiness = userBusiness;
    }

    /**
     * Get the list of active items.
     * @return A list of active items.
     */
    public List<OrderRequest> retrieveOrders() {
        List<OrderRequest> list = new ArrayList<>();

        orderRepository.findAllActive().forEach(o -> list.add(modelMapper.map(o, OrderRequest.class)));

        return list;
    }

    /**
     * 
     * @param id
     * @return
     */
    public Long inactiveOrderById(Long id) {
        Optional<OrderEntity> optionalUserEntity = orderRepository.findActiveById(id);

        if(optionalUserEntity.isPresent()) {
            optionalUserEntity.get().setActive(false);

            optionalUserEntity.get().getOrderItemsEntity().forEach(e -> {
                e.setActive(false);
                e.getMovements().forEach(m -> m.setActive(false));
            });

            orderRepository.save(optionalUserEntity.get());

            return id;
        }
        else {
            return null;
        }
    }

    /**
     * 
     * @param order
     * @return
     * @throws OrderManagerException
     */
    public OrderEntity save(OrderDTO order) throws OrderManagerException {
        OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);

        orderEntity.setUserEntity(findUserEntityByUserDTO(order.getUser()));

        List<OrderItemEntity> orderItems = findOrderItemsEntityByOrderItems(orderEntity, order.getOrderItems());

        orderItems.forEach(oi -> oi.setActive(true));

        orderEntity.setOrderItemsEntity(orderItems);
        
        try {
            orderEntity = orderRepository.save(orderEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new OrderManagerException(e);
        }

        return orderEntity;
    }

    private UserEntity findUserEntityByUserDTO(UserDTO user) throws OrderManagerException {
        Optional<UserDTO> userOptional = userBusiness.findUserByEmail(user.getEmail());

        if(!userOptional.isPresent()) {
            throw new OrderManagerException(String.format("User does not exist: %s", user));
        }

        return modelMapper.map(userOptional.get(), UserEntity.class);
    }

    private List<OrderItemEntity> findOrderItemsEntityByOrderItems(OrderEntity orderEntity, List<OrderItemDTO> orderItems) throws OrderManagerException {
        if(orderEntity == null) {
            throw new OrderManagerException("It connot find OrderItemsEntity by OrderItems because OrderEntity is null: %s");
        }

        if(orderItems == null) {
            throw new OrderManagerException("Order item list is null: %s");
        }

        List<OrderItemEntity> orderItemsEntity = new ArrayList<>();

        for(OrderItemDTO orderItemDTO: orderItems) { 
            ItemEntity itemEntity = orderItemComponent.findItemByOrderItemDTO(orderItemDTO);

            if(itemEntity == null) {
                continue;
            }

            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setOrderEntity(orderEntity);
            orderItemEntity.setItemEntity(itemEntity); 
            orderItemEntity.setQuantity(orderItemDTO.getQuantity());
            orderItemEntity.setMovements(findStockMovementsEntityByOrderItemDTO(orderItemDTO, itemEntity, orderItemEntity));
        
            orderItemsEntity.add(orderItemEntity);
        }
        
        return orderItemsEntity;
    }

    private List<StockMovementEntity> findStockMovementsEntityByOrderItemDTO(OrderItemDTO orderItemDto, ItemEntity itemEntity, OrderItemEntity orderItemEntity) throws OrderManagerException {
        if(orderItemDto == null) {
            throw new OrderManagerException("It connot find StockMovements entity because OrderItemDTO is null: %s");
        }

        if(orderItemDto.getMovements() == null || orderItemDto.getMovements().isEmpty()) {
            throw new OrderManagerException("It connot find StockMovements entity because OrderItemDTO movements do not exist: %s");
        }

        List<StockMovementEntity> stockMovementEntityList = new ArrayList<>();

        for(StockMovementDTO stockMovementDTO: orderItemDto.getMovements()) {
            try {
                StockMovementEntity stockMovementEntity = new StockMovementEntity(
                        null,
                        stockMovementDTO.getCreationDate(),
                        itemEntity,
                        null,
                        stockMovementDTO.getQuantity(),
                        true
                    );

                stockMovementRepository.save(stockMovementEntity);
                stockMovementEntity.setOrderItemEntity(orderItemEntity);

                stockMovementEntityList.add(stockMovementEntity);
            }
            catch(Exception e) {
                throw new OrderManagerException(e);
            }
        }

        return stockMovementEntityList;
    }

    /**
     * This method retrive order from repository based in the src id an try to update the order.
     * @param src The order used to update.
     * @return Return the target of update, the order retrived from repository.
     * @throws OrderManagerException
     */
    public Optional<OrderDTO> updateEntity(OrderDTO src) throws OrderManagerException {
        OrderDTO tar = null;
        Optional<OrderEntity> optionalOrder = orderRepository.findActiveById(src.getId());

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
