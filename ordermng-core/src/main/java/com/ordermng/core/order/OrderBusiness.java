package com.ordermng.core.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.ordermng.core.dto.OrderDTO;
import com.ordermng.core.dto.OrderItemDTO;
import com.ordermng.core.dto.OrderType;
import com.ordermng.core.dto.StockAmountDTO;
import com.ordermng.core.dto.StockMovementDTO;
import com.ordermng.core.dto.UserDTO;
import com.ordermng.core.orderitem.OrderItemBusiness;
import com.ordermng.core.user.UserBusiness;

public interface OrderBusiness {
    /**
     * 
     */
    public default OrderDTO newOrder(UserDTO user, List<OrderItemDTO> orderItems, OrderType type, OrderItemBusiness orderItemBusiness) {
        return this.satisfyOrder(
            new OrderDTO(null, LocalDateTime.now(), user, type, orderItems), 
            orderItemBusiness.retriveStockAmountFromOrderItems(orderItems),
            orderItemBusiness);
    }

    /**
     * Update the target order based on source one. This methos centralized 
     * what is suppose to update in an order and do not change attributes that identify the order. 
     * @param tar Target object
     * @param src Source object
     */
    public default void updateEntity(OrderDTO tar, OrderDTO src) {
        if(!tar.getId().equals(src.getId())) {
            return;
        }
        
        tar.setCreationDate(src.getCreationDate());
        tar.setActive(src.getActive());
        tar.setShipped(src.getShipped());
        tar.setUser(new UserDTO(src.getUser()));

        tar.getOrderItems().clear();
        src.getOrderItems().forEach(oi -> tar.getOrderItems().add(new OrderItemDTO(oi)));
    }

    public default OrderDTO satisfyOrder(OrderDTO order, List<StockAmountDTO> stockAmounts, OrderItemBusiness orderItemBusiness) {
        if(order == null || order.getOrderItems() == null || order.getOrderItems().isEmpty() ||
                stockAmounts == null) {
            return order;
        }

        if(order.getType() == OrderType.SALE) {
            order.getOrderItems().stream().filter(o -> !orderItemBusiness.isSatisfied(o)).forEach(orderItem ->
                stockAmounts.stream().filter(a -> a.getItem().equals(orderItem.getItem())).forEach(stockAmount -> {
                    double necessity = orderItem.getQuantity() - orderItemBusiness.getMovimentAmount(orderItem);

                    double quantity = stockAmount.getAmount() > necessity ? necessity : stockAmount.getAmount();
                    
                    orderItemBusiness.addStockMovement(orderItem, new StockMovementDTO(orderItem.getItem(), orderItem, LocalDateTime.now(), quantity, true));
                })
            );
        } 
        else if(order.getType() == OrderType.PURCHASE) {
            order.getOrderItems().forEach(oi ->
                orderItemBusiness.addStockMovement(oi, new StockMovementDTO(oi.getItem(), oi, LocalDateTime.now(), oi.getQuantity(), true)));
        }

        return order;
    }

    public default boolean isValid(OrderDTO order, UserBusiness userBusiness) {
        return order.getCreationDate() != null &&
            userBusiness.isValid(order.getUser()) &&
            order.getActive() != null;
    }
}
