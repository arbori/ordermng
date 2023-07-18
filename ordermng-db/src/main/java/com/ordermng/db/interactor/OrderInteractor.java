package com.ordermng.db.interactor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ordermng.core.OrderManagerException;
import com.ordermng.core.domine.Order;
import com.ordermng.core.uc.OrderUseCase;
import com.ordermng.db.ItemEntity;
import com.ordermng.db.OrderEntity;
import com.ordermng.db.UserEntity;
import com.ordermng.db.repository.ItemRepository;
import com.ordermng.db.repository.OrderRepository;
import com.ordermng.db.repository.UserRepository;

@Component
public class OrderInteractor {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    
    @Autowired
    public OrderInteractor(OrderRepository orderRepository, ItemRepository itemRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public Order save(Order order) throws OrderManagerException {
        OrderUseCase.validate(order);

        Optional<ItemEntity> optionalItemEntity = itemRepository.findById(order.getItem().getId());

        if(!optionalItemEntity.isPresent()) {
            throw new OrderManagerException("The item associated to the order does not exist.");
        }

        Optional<UserEntity> optionalUserEntity = userRepository.findById(order.getUser().getId());

        if(!optionalUserEntity.isPresent()) {
            throw new OrderManagerException("The uset associated to the order does not exist.");
        }

        OrderEntity entity =  new OrderEntity();
        entity.setCreationDate(order.getCreationDate()); 
        entity.setItem(optionalItemEntity.get()); 
        entity.setQuantity(order.getQuantity());
        entity.setUser(optionalUserEntity.get());
        entity.setActive(true);

        entity = orderRepository.save(entity);

        order.setId(entity.getId());
        order.setActive(entity.getActive());

        return order;
    }

    public void delete(Long id) throws OrderManagerException {
        Optional<OrderEntity> optionalEntity = orderRepository.findById(id);

        if(optionalEntity.isPresent() && optionalEntity.get().getActive().booleanValue()) {
            optionalEntity.get().setActive(false);

            orderRepository.save(optionalEntity.get());
        } else {
            throw new OrderManagerException(String.format("The order with id %d does not exist.", id));
        }
    }

    public List<Order> listOrders() {
        List<Order> orders = new ArrayList<>();

        orderRepository.findAll().forEach(o -> {
            if(o.getActive().booleanValue()) {
                orders.add(o.toDomine());
            }
        });

        return orders;
    }

    public Order updateOrder(Order order) throws OrderManagerException {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(order.getId());

        if(!optionalOrder.isPresent()) {
            throw new OrderManagerException("Order does not exist to be changed.");
        }

        optionalOrder.get().setQuantity(order.getQuantity());
        orderRepository.save(optionalOrder.get());

        return optionalOrder.get().toDomine();
    }
}
