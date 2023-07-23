package com.ordermng.api.transform;

import com.ordermng.api.model.Item;
import com.ordermng.db.item.ItemEntity;

public class ItemTransform {
    private ItemTransform() {
    }

    public static ItemEntity requestToEntity(Item itemRequest) {
        return new ItemEntity(
            itemRequest.getCode(),
            itemRequest.getName(),
            true
        );
    }    

    public static Item entityToResponse(ItemEntity itemEntity) {
        Item itemResponse = new Item();

        itemResponse.setCode(itemEntity.getCode());
        itemResponse.setName(itemEntity.getName());

        return itemResponse;
    }

    public static void updateEntity(ItemEntity tar, ItemEntity src) {
        tar.setName(src.getName());
    }
}
