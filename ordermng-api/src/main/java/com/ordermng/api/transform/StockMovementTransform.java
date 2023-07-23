package com.ordermng.api.transform;

import com.ordermng.api.model.Item;
import com.ordermng.api.model.StockMovement;
import com.ordermng.db.item.ItemEntity;
import com.ordermng.db.movement.StockMovementEntity;

public class StockMovementTransform {
    private StockMovementTransform() {
    }

    public static StockMovementEntity requestToEntity(StockMovement stockMovementRequest) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setCode(stockMovementRequest.getItem().getCode());

        return new StockMovementEntity(
            stockMovementRequest.getId(),
            stockMovementRequest.getCreationDate(),
            itemEntity,
            stockMovementRequest.getQuantity(),
            true
        );
    }    

    public static StockMovement entityToResponse(StockMovementEntity stockMovementEntity) {
        Item itemResponse = new Item();

        itemResponse.setCode(stockMovementEntity.getItem().getCode());
        itemResponse.setName(stockMovementEntity.getItem().getName());

        StockMovement stockMovement = new StockMovement();
        stockMovement.setId(stockMovementEntity.getId());
        stockMovement.setCreationDate(stockMovementEntity.getCreationDate());
        stockMovement.setItem(itemResponse);
        stockMovement.setQuantity(stockMovementEntity.getQuantity());

        return stockMovement;
    }

    public static void updateEntity(ItemEntity tar, ItemEntity src) {
        tar.setName(src.getName());
    }

    public static void updateEntity(StockMovementEntity tar, StockMovementEntity src) {
        tar.setId(src.getId());
        tar.setCreationDate(src.getCreationDate());
        tar.setItem(src.getItem());
        tar.setQuantity(src.getQuantity());
        tar.setActive(src.getActive());
    }
}
