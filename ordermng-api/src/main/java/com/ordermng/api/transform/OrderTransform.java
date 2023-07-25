package com.ordermng.api.transform;

import java.util.ArrayList;
import java.util.List;

import com.ordermng.api.model.Item;
import com.ordermng.api.model.Order;
import com.ordermng.api.model.OrderItem;
import com.ordermng.api.model.StockMovement;
import com.ordermng.db.item.ItemEntity;
import com.ordermng.db.movement.StockMovementEntity;
import com.ordermng.db.order.OrderEntity;
import com.ordermng.db.order.OrderItemEntity;
import com.ordermng.db.user.UserEntity;

public class OrderTransform {
    private OrderTransform() {
    }

    public static OrderEntity requestToEntity(Order orderRequest) {
        OrderEntity orderEntity = new OrderEntity();

        UserEntity userEntity = UserTransform.requestToEntity(orderRequest.getUser());

        // List<OrderItemEntity> orderItemsEntity = new ArrayList<>();
        
        // orderRequest.getOrderItems().forEach(oapi -> {
        //     OrderItemEntity orderItemEntity = OrderTransform.requestToEntity(oapi);
        //     orderItemEntity.setOrderEntity(orderEntity);

        //     orderItemsEntity.add(orderItemEntity);
        // });

        orderEntity.setId(orderRequest.getId());
        orderEntity.setCreationDate(orderRequest.getCreationDate());
        orderEntity.setUser(userEntity);
        // orderEntity.setOrderItemsEntity(orderItemsEntity);

        return orderEntity;
    }    

    public static OrderItemEntity requestToEntity(OrderItem oapi) {
        return new OrderItemEntity(ItemTransform.requestToEntity(oapi.getItem()), oapi.getQuantity());
    }

    private static OrderItem entityToResponse(OrderItemEntity entity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(ItemTransform.entityToResponse((entity.getItemEntity())));
        orderItem.setQuantity(entity.getQuantity());
        
        List<StockMovement> movements = new ArrayList<>();
        entity.getMovements().forEach(m -> movements.add(StockMovementTransform.entityToResponse((StockMovementEntity) m)));
        orderItem.setMovements(movements);
    
        return orderItem;
    }

    public static Item entityToResponse(ItemEntity itemEntity) {
        Item itemResponse = new Item();

        itemResponse.setCode(itemEntity.getCode());
        itemResponse.setName(itemEntity.getName());

        return itemResponse;
    }

    public static Order entityToResponse(OrderEntity o) {
        List<OrderItem> orderItems = new ArrayList<>();
        
        o.getOrderItems().forEach(orderEntity -> orderItems.add(OrderTransform.entityToResponse((OrderItemEntity) orderEntity)));

        return new Order().id(o.getId())
            .creationDate(o.getCreationDate())
            .user(UserTransform.entityToResponse((UserEntity) o.getUser()))
            .orderItems(orderItems)
            .shipped(o.getShipped())
            .active(o.getActive());
    }

    public static void updateEntity(ItemEntity tar, ItemEntity src) {
        tar.setName(src.getName());
    }

    public static void updateEntity(OrderEntity tar, OrderEntity src) {
        tar.setCreationDate(src.getCreationDate());
        tar.setActive(src.getActive());
        tar.setShipped(src.getShipped());
        tar.setUser(src.getUser());

        tar.getOrderItemsEntity().clear();
        tar.getOrderItemsEntity().addAll(src.getOrderItemsEntity());
    }
}
