package com.ordermng.core;

import com.ordermng.db.Item;

public class ItemBusiness {
    private static final int NAME_SIZE = 14;

    private ItemBusiness() {
    }

    public static boolean validate(Item item) {
        return item != null &&
            item.getName() != null &&
            !item.getName().isEmpty() &&
            item.getName().length() <= NAME_SIZE &&
            item.getActive() != null;
    }
}
