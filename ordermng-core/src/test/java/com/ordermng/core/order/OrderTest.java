package com.ordermng.core.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import com.ordermng.core.dto.ItemDTO;
import com.ordermng.core.dto.OrderDTO;
import com.ordermng.core.dto.OrderItemDTO;
import com.ordermng.core.dto.OrderType;
import com.ordermng.core.dto.StockAmountDTO;
import com.ordermng.core.dto.UserDTO;
import com.ordermng.core.orderitem.OrderItemBusiness;

/**
 * Unit test for simple App.
 */
public class OrderTest {
    UserDTO user;

    @org.junit.Before
    public void before() {
        user = new UserDTO("meuemail@servidor.com", "Meu Nome Completo", true);
    }

    @Test
    public void newOrderSatisfied() {
        OrderBusiness orderBusiness = new OrderBusiness() {};
        OrderItemBusiness orderItemBusiness = new OrderItemBusiness() {
            @Override
            public List<StockAmountDTO> retriveStockAmountFromOrderItems(List<OrderItemDTO> orderItemsList) {
                return Arrays.asList(
                    new StockAmountDTO[] {
                        new StockAmountDTO(orderItemsList.get(0).getItem(), orderItemsList.get(0).getQuantity()),
                        new StockAmountDTO(orderItemsList.get(1).getItem(), orderItemsList.get(1).getQuantity())
                    }
                );
            }

            @Override
            public List<OrderItemDTO> retrieveUnsatisfiedOrderItemsByItem(ItemDTO item) {
                return null;
            }
        };
        
        ItemDTO[] items = new ItemDTO[] {
            new ItemDTO("MAT4526-R", "Martelo", true),
            new ItemDTO("PAF987-A2", "Parafuso", true)
        };

        List<OrderItemDTO> orderItems = new ArrayList<>();
        orderItems.add(new OrderItemDTO(items[0], 10.0, null));
        orderItems.add(new OrderItemDTO(items[1], 13.0, null));

        OrderDTO order = orderBusiness.newOrder(user, orderItems, OrderType.SALE, orderItemBusiness);

        assertNotNull("OrderDTO was not created.", order);
        assertNotNull("There is no user defined in the order", order.getUser());
        assertNotNull("There is no items list defined in the order", order.getOrderItems());
        assertTrue("There are no items in the order", !order.getOrderItems().isEmpty());
        assertEquals("The list of items is wrong.", items.length, order.getOrderItems().size());

        order.getOrderItems().stream().forEach( o -> {
            assertTrue(String.format("The Order item %s was not satisfyed.", o.getItem()), orderItemBusiness.isSatisfied(o));
        });
    }

    @Test
    public void newOrderNotAllSatisfied() {
        OrderBusiness orderBusiness = new OrderBusiness() {};
        OrderItemBusiness orderItemBusiness = new OrderItemBusiness() {
            @Override
            public List<StockAmountDTO> retriveStockAmountFromOrderItems(List<OrderItemDTO> orderItemsList) {
                return Arrays.asList(
                    new StockAmountDTO[] {
                        new StockAmountDTO(orderItemsList.get(0).getItem(), orderItemsList.get(0).getQuantity()),
                        new StockAmountDTO(orderItemsList.get(1).getItem(), .8*orderItemsList.get(1).getQuantity())
                    }
                );
            }

            @Override
            public List<OrderItemDTO> retrieveUnsatisfiedOrderItemsByItem(ItemDTO item) {
                return null;
            }
        };
        
        ItemDTO[] items = new ItemDTO[] {
            new ItemDTO("MAT4526-R", "Martelo", true),
            new ItemDTO("PAF987-A2", "Parafuso", true)
        };

        List<OrderItemDTO> orderItems = new ArrayList<>();
        orderItems.add(new OrderItemDTO(items[0], 10.0, null));
        orderItems.add(new OrderItemDTO(items[1], 13.0, null));

        OrderDTO order = orderBusiness.newOrder(user, orderItems, OrderType.SALE, orderItemBusiness);

        assertNotNull("OrderDTO was not created.", order);
        assertNotNull("There is no user defined in the order", order.getUser());
        assertNotNull("There is no items list defined in the order", order.getOrderItems());
        assertTrue("There are no items in the order", !order.getOrderItems().isEmpty());
        assertEquals("The list of items is wrong.", items.length, order.getOrderItems().size());

        assertTrue(String.format("The Order item %s was not satisfyed.", order.getOrderItems().get(0).getItem()), 
            orderItemBusiness.isSatisfied(order.getOrderItems().get(0)));

        assertTrue(String.format("The Order item %s was satisfyed, but suppose not to be.", 
            order.getOrderItems().get(1).getItem()), 
            !orderItemBusiness.isSatisfied(order.getOrderItems().get(1)));
    }

    @Test
    public void newOrderBoughtItems() {
        OrderBusiness orderBusiness = new OrderBusiness() {};
        OrderItemBusiness orderItemBusiness = new OrderItemBusiness() {
            @Override
            public List<StockAmountDTO> retriveStockAmountFromOrderItems(List<OrderItemDTO> orderItemsList) {
                return new ArrayList<>();
            }

            @Override
            public List<OrderItemDTO> retrieveUnsatisfiedOrderItemsByItem(ItemDTO item) {
                return new ArrayList<>();
            }
        };

        List<OrderItemDTO> orderItems = new ArrayList<>();
        orderItems.add(new OrderItemDTO(
            new ItemDTO("MAT4526-R", "Martelo", true), 10.0, null));
        orderItems.add(new OrderItemDTO(
            new ItemDTO("PAF987-A2", "Parafuso", true), 13.0, null));

        OrderDTO order = orderBusiness.newOrder(user, orderItems, OrderType.PURCHASE, orderItemBusiness);

        for(OrderItemDTO oi: order.getOrderItems()) {
            assertTrue("An order item was not satisfied in a purchase order", orderItemBusiness.isSatisfied(oi));
        }
    }
}
