package com.ordermng.core.order;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import com.ordermng.core.item.Item;
import com.ordermng.core.user.User;

/**
 * Unit test for simple App.
 */
public class OrderTest {
    @Test
    public void satisfyOrder() {
        User user = new User("meuemail@servidor.com", "Meu Nome Completo", true);

        Item[] items = new Item[] {
            new Item("MAT4526-R", "Martelo", true),
            new Item("PAF987-A2", "Parafuso", true)
        };

        List<OrderItem> orderItemsList = new ArrayList<>();
        orderItemsList.add(new OrderItem(items[0], 10.0));
        orderItemsList.add(new OrderItem(items[1], 13.0));
        
        Order order = new Order(1L, LocalDateTime.now(), user, orderItemsList);

        List<StockAmount> stockAmounts = new ArrayList<>();
        stockAmounts.add(new StockAmount(items[0], 200.0));
        stockAmounts.add(new StockAmount(items[1], 137.0));

        OrderUseCase.satisfyOrder(order, stockAmounts);

        orderItemsList.stream().forEach( o -> {
            assertTrue(String.format("The Order item %s was not satisfyed.", o.getItem()), o.isSatisfied());
        });
    }
}
