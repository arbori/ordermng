package com.ordermng.core.movement;

import java.util.Optional;

import com.ordermng.core.dto.StockMovementDTO;
import com.ordermng.core.item.ItemBusiness;
import com.ordermng.core.orderitem.OrderItemBusiness;

public interface StockMovementBusiness {
    /**
     * 
     * @param stockMovement
     * @return
     */
    public String addNewStockMovement(StockMovementDTO stockMovement);

    /**
     * It is not supposed to remove an stock movement from database. Instead, it is turned inactive and cannot be used in any order anymore.
     * @param item An stock movement to be inactive.
     * @return Return the code of the inactived item.
     */
    public Optional<Long> inactiveStockMovement(StockMovementDTO stockMovement);
    
    /**
     * This method check if a particular stock movement can be inactivated based on the list below.
     * <ul>
     * <li> A stock movement with a set order item already satisfied, cannot be inactivated;
     * <li> A stock movement cannot be incativated if the amount for the item was used to satisfied order itens;
     * </ul>
     * @param stockMovement
     * @return
     */
    boolean itCanBeInactivated(StockMovementDTO stockMovement); 

    /**
     * 
     * @param stockMovement
     * @param orderItemBusiness
     * @return
     */
    public default boolean isValid(StockMovementDTO stockMovement, OrderItemBusiness orderItemBusiness, ItemBusiness itemBusiness) {
        boolean result = stockMovement != null &&
            stockMovement.getCreationDate() != null &&
            stockMovement.getQuantity() != null && stockMovement.getQuantity() > 0.0 &&
            stockMovement.getActive() != null &&
            itemBusiness.isValid(stockMovement.getItem());

            if(result && stockMovement.getOrderItem() != null) {
                result &= orderItemBusiness.isValid(stockMovement.getOrderItem()) &&
                    stockMovement.getOrderItem().getItem().equals(stockMovement.getItem());
            }

            return result;
    }
}
