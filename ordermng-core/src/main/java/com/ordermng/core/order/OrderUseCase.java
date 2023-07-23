package com.ordermng.core.order;

import java.time.LocalDateTime;
import java.util.List;

import com.ordermng.core.movement.StockMovement;

public class OrderUseCase {
    private OrderUseCase() {
    }

    public static void satisfyOrder(Order order, List<StockAmount> stockAmounts) {
        order.getOrderItemsList().stream().filter(o -> !o.isSatisfied()).forEach(o -> {
            stockAmounts.stream().filter(a -> a.getItem().equals(o.getItem())).forEach(a -> {
                double quantity = a.getAmount() > (o.getQuantity() - o.getAmountMoviment()) ?
                    o.getQuantity() - o.getAmountMoviment() :
                    a.getAmount();
                
                o.addStockMovement(new StockMovement(null, LocalDateTime.now(), o.getItem(), quantity, true));
            });
        });
    }
}
