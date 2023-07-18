package com.ordermng.core.uc;

import com.ordermng.core.OrderManagerException;
import com.ordermng.core.domine.Order;

public class OrderUseCase {
    private OrderUseCase() {
    }

    public static void validate(Order order) throws OrderManagerException {
        StringBuilder sb = new StringBuilder();

        if(order == null || order.getCreationDate() == null || order.getQuantity() == null) {
            sb.append("A null order or one without asked attributes, cannot be saved: ").append(order);

            throw new OrderManagerException(sb.toString());
        }

        if(order.getQuantity() <= 0) {
            sb.delete(0, sb.length()).append("An order must have a positive quantity: ").append(order);

            throw new OrderManagerException(sb.toString());
        }

        if(!ItemUseCase.isValid(order.getItem()) || order.getItem().getId() == null) {
            sb.append("An order must have a valid, existing item associated with it: ").append(order);

            throw new OrderManagerException(sb.toString());
        }

        if(!UserUseCase.isValid(order.getUser()) || order.getUser().getId() == null) {
            sb.append("An order must have a valid, existing user associated with it: ").append(order);

            throw new OrderManagerException(sb.toString());
        }
    }
}