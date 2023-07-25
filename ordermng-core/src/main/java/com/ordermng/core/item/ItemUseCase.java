package com.ordermng.core.item;

public class ItemUseCase {
    private static final int CODE_SIZE = 12;
    private static final int NAME_SIZE = 40;

    private static final double POSITIVE_MIN = 10E-7;
    private static final double NEGATIVE_MIN = -POSITIVE_MIN;

    private ItemUseCase() {
    }

    public static boolean isValid(Item item) {
        boolean valid = item.isValid() && item.getCode() != null && item.getCode().length() <= CODE_SIZE && item.getName().length() <= NAME_SIZE;

        if(valid) {
            try {
                Long.parseLong(item.getCode());
            } catch(NumberFormatException e) {
                valid = false;
            }
        }

        return valid;
    }

    public static boolean isZero(double value) {
        return NEGATIVE_MIN < value && value < POSITIVE_MIN;
    }
}
