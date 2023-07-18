package com.ordermng.api.transform;

import com.ordermng.db.ItemEntity;

public class ItemTransform {
    private ItemTransform() {
    }

    public static com.ordermng.core.domine.Item apiModelToDomine(com.ordermng.api.model.Item body) {
        return new com.ordermng.core.domine.Item(
            body.getId(),
            body.getName(),
            body.isActive()
        );
    }    

    public static com.ordermng.api.model.Item entityToApiModel(ItemEntity item) {
        com.ordermng.api.model.Item body = new com.ordermng.api.model.Item();

        body.setId(item.getId());
        body.setName(item.getName());
        body.setActive(item.getActive());

        return body;
    }

    public static void updateEntity(ItemEntity itemEntity, com.ordermng.core.domine.Item item) {
        itemEntity.setName(item.getName());
        itemEntity.setActive(item.getActive());
    }
}
