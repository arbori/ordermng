package com.ordermng.api.transform;

public class ItemTransform {
    private ItemTransform() {
    }

    public static com.ordermng.api.model.Item model2api(com.ordermng.db.Item item) {
        com.ordermng.api.model.Item body = new com.ordermng.api.model.Item();

        body.setId(item.getId());
        body.setName(item.getName());
        body.setActive(item.getActive());

        return body;
    }

    public static com.ordermng.db.Item api2model(com.ordermng.api.model.Item body) {
        return new com.ordermng.db.Item(
            body.getId(),
            body.getName(),
            body.isActive()
        );
    }    
}
