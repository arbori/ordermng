package com.ordermng.core.user;

public class UserUseCase {
    private static final int EMAIL_SIZE = 32;
    private static final int NAME_SIZE = 40;

    private UserUseCase() {
    }

    public static boolean isValid(User user) {
        return user.isValid() && user.getEmail().length() <= EMAIL_SIZE && user.getName().length() <= NAME_SIZE;
    }
}
