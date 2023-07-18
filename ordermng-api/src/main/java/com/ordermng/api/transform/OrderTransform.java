package com.ordermng.api.transform;

import org.threeten.bp.OffsetDateTime;

import com.ordermng.api.model.Item;
import com.ordermng.api.model.User;
import com.ordermng.core.domine.Order;
import com.ordermng.db.ItemEntity;
import com.ordermng.db.OrderEntity;
import com.ordermng.db.UserEntity;

import java.time.format.DateTimeFormatter;

public class OrderTransform {
    private OrderTransform() {
    }

    public static com.ordermng.core.domine.Order apiModelToDomine(com.ordermng.api.model.Order body) {
        org.threeten.bp.LocalDateTime bodyDate = body.getCreationDate().toLocalDateTime();
        java.time.LocalDateTime orderDate = java.time.LocalDateTime.parse(bodyDate.toString());

        com.ordermng.core.domine.Item item = new com.ordermng.core.domine.Item();
        item.setId(body.getItem().getId());
        item.setName(body.getItem().getName());
        item.setActive(body.getItem().isActive());
        
        com.ordermng.core.domine.User user = new com.ordermng.core.domine.User();
        user.setId(body.getUser().getId());
        user.setName(body.getUser().getName());
        user.setEmail(body.getUser().getEmail());
        user.setActive(body.getUser().isActive());

        com.ordermng.core.domine.Order order = new com.ordermng.core.domine.Order();
        order.setId(body.getId());
        order.setCreationDate(orderDate);
        order.setItem(item);
        order.setQuantity(order.getQuantity());
        order.setUser(user);
        order.setActive(body.isActive());

        return order;
    }

    public static com.ordermng.api.model.Order domineToApiModel(com.ordermng.core.domine.Order order) {
        com.ordermng.api.model.Order body = new com.ordermng.api.model.Order();

        Item item = new Item();
        item.setId(order.getItem().getId());
        item.setName(order.getItem().getName());
        item.setActive(order.getItem().getActive());

        User user = new User();
        user.setId(order.getUser().getId());
        user.setName(order.getUser().getName());
        user.setEmail(order.getUser().getEmail());
        user.setActive(order.getUser().getActive());

        body.setId(order.getId());
        body.setCreationDate(OffsetDateTime.parse(order.getCreationDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
        body.setItem(item);
        body.setQuantity(order.getQuantity());
        body.setUser(user);
        body.setActive(order.getActive());
        
        return body;
    }

    public static void updateEntity(OrderEntity entity, Order order) {
        entity.setCreationDate(order.getCreationDate());
        entity.setQuantity(order.getQuantity());
        entity.setActive(order.getActive());

        entity.setItem(new ItemEntity(order.getItem()));
        entity.setUser(new UserEntity(order.getUser()));
    }
}