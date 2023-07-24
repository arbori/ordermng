package com.ordermng.core.order;

import java.time.LocalDateTime;
import java.util.List;

import com.ordermng.core.item.Item;
import com.ordermng.core.item.ItemUseCase;
import com.ordermng.core.movement.StockMovement;

public class OrderUseCase {
    private OrderUseCase() {
    }

    public static void satisfyOrder(Order order, List<StockAmount> stockAmounts) {
        order.getOrderItems().stream().filter(o -> !o.isSatisfied()).forEach(o -> {
            stockAmounts.stream().filter(a -> a.getItem().equals(o.getItem())).forEach(a -> {
                double quantity = a.getAmount() > (o.getQuantity() - o.getAmountMoviment()) ?
                    o.getQuantity() - o.getAmountMoviment() :
                    a.getAmount();
                
                o.addStockMovement(new StockMovement(LocalDateTime.now(), o.getItem(), quantity, true));
            });
        });
    }

    public static boolean isValid(OrderItem orderItem) {
        boolean result = ItemUseCase.isValid(orderItem.getItem()) &&
            orderItem.getQuantity() != null && orderItem.getQuantity() > 0.0 &&
            orderItem.getMovements() != null;

        result &= orderItem.getQuantity() - orderItem.getAmountMoviment() >= 0.0;

        return result;
    }
}
