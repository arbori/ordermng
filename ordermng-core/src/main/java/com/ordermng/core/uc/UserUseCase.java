package com.ordermng.core.uc;

import com.ordermng.core.domine.User;

public class UserUseCase {
    private static final int NAME_SIZE = 25;
    private static final int EMAIL_SIZE = 32;

    private UserUseCase() {
    }
    
    public static boolean isValid(User user) {
        return user != null &&
            user.getName() != null &&
            !user.getName().isEmpty() &&
            user.getName().length() <= NAME_SIZE &&
            user.getEmail() != null &&
            !user.getEmail().isEmpty() &&
            user.getEmail().length() <= EMAIL_SIZE &&
            user.getActive() != null;
    }
}
