package com.ordermng.core.uc;

import com.ordermng.core.domine.Item;

public class ItemUseCase {
    private static final int NAME_SIZE = 40;

    private ItemUseCase() {
    }

    public static boolean isValid(Item item) {
        return item != null &&
            item.getName() != null &&
            !item.getName().isEmpty() &&
            item.getName().length() <= NAME_SIZE &&
            item.getActive() != null;
    }
}
