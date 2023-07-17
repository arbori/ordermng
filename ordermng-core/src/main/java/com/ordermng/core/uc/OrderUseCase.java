package com.ordermng.core.uc;

import com.ordermng.core.domine.Order;

public class OrderUseCase {
    private static final int NAME_SIZE = 14;

    private OrderUseCase() {
    }

    public static boolean isValid(Order order) {
        return false;
    }
}