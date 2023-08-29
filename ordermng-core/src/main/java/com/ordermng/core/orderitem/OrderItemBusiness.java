package com.ordermng.core.orderitem;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.ordermng.core.dto.ItemDTO;
import com.ordermng.core.dto.OrderItemDTO;
import com.ordermng.core.dto.StockAmountDTO;
import com.ordermng.core.dto.StockMovementDTO;

public interface OrderItemBusiness {
    /**
     * 
     * @param orderItemsList
     * @return
     */
    public List<StockAmountDTO> retriveStockAmountFromOrderItems(List<OrderItemDTO> orderItemsList);

    /**
     * Looking for order itens did not complet yet. It means, with at last one OrderItem did not satisfied.
     * @return
     */
    public List<OrderItemDTO> retrieveUnsatisfiedOrderItemsByItem(ItemDTO item);

    /**
     * 
     * @param stockMovement
     */
    public default void satisfyOrderItems(StockMovementDTO stockMovement) {
        List<OrderItemDTO> unsatisfiedOrderItens = retrieveUnsatisfiedOrderItemsByItem(stockMovement.getItem());

        Collections.sort(unsatisfiedOrderItens, (OrderItemDTO var1, OrderItemDTO var2) -> 
            (int) (var1.getQuantity() - var2.getQuantity()));
    
        double availableQuantity = stockMovement.getQuantity();

        for(OrderItemDTO orderItem: unsatisfiedOrderItens) {
            availableQuantity -= addStockMovement(
                orderItem, 
                new StockMovementDTO(
                    stockMovement.getItem(), 
                    orderItem, 
                    LocalDateTime.now(), 
                    availableQuantity, 
                    true));
            
            if(availableQuantity <= 0.0) {
                break;
            }
        }
    }

    /**
     * An order item may not be fulfilled at the same time the order is entered. The addStockMovement method provides means to associate stock movements that aim to meet the request for an item, contained in an order. 
     * The stock movement received must to be at the maximum the difference between the quantity asked for the item and the sum of the set of stock movement already associated to the OrderItem object. Therefore, any stock movement with the quantity higher then that difference will have its quantity changed to be enogth to fulfiled the order item, or will not be inserted if ordem item is alread satisfied.
     * 
     * @param stockMovement The stock movement that aim to meet the need of an item.
     */
    public default double addStockMovement(OrderItemDTO orderItem, StockMovementDTO stockMovement) {
        double missing = orderItem.getQuantity() - getMovimentAmount(orderItem);
        
        if(missing > 0.0) {
            if(stockMovement.getQuantity() > missing) {
                stockMovement.setQuantity(missing);
            }

            orderItem.getMovements().add(stockMovement);
        }

        return stockMovement.getQuantity();
    }
    
    /**
     * 
     * @param orderItem
     * @return
     */
    public default boolean isValid(OrderItemDTO orderItem) {
        boolean result = 
            orderItem.getQuantity() != null && orderItem.getQuantity() > 0.0 &&
            orderItem.getMovements() != null;

        result &= orderItem.getQuantity() - getMovimentAmount(orderItem) >= 0.0;

        return result;
    }

    static final double POSITIVE_MIN = 10E-7;
    static final double NEGATIVE_MIN = -POSITIVE_MIN;

    /**
     * Check if the order item object has fulfilled. Its means, the sum of StockMovements' quantities are enough to satisfy the quantity in order item. 
     * @return Return true if object has been satisfied.
     */
    public default boolean isSatisfied(OrderItemDTO orderItem) {
        double amount = getMovimentAmount(orderItem);
        double difference = amount - orderItem.getQuantity();

        return NEGATIVE_MIN < difference && difference < POSITIVE_MIN;
    }    

    /**
     * Computes the sum of stock movements'.
     * @return The total of quantities.
     */
    public default double getMovimentAmount(OrderItemDTO orderItem) {
        return orderItem.getMovements().stream().mapToDouble(m -> m.getQuantity()).sum();
    }
}
