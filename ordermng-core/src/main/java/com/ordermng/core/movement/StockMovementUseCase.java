package com.ordermng.core.movement;

public class StockMovementUseCase {
    private StockMovementUseCase() {
    }

    public static boolean isValid(StockMovement stockMovement) {
        return stockMovement != null &&
            stockMovement.getCreationDate() != null &&
            stockMovement.getQuantity() != null && stockMovement.getQuantity() > 0.0 &&
            stockMovement.getActive() != null &&
            stockMovement.getItem() != null;
    }
}
