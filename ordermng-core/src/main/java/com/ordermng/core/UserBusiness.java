package com.ordermng.core;

import com.ordermng.db.User;

public class UserBusiness {
    private UserBusiness() {

    }
    
    public static boolean validate(User user) {
        return user != null &&
            user.getName() != null &&
            !user.getName().isEmpty() &&
            user.getEmail() != null &&
            !user.getEmail().isEmpty() &&
            user.getActive() != null;
    }
}
