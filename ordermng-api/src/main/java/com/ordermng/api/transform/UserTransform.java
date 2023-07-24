package com.ordermng.api.transform;

import com.ordermng.api.model.User;
import com.ordermng.db.user.UserEntity;

public class UserTransform {
    private UserTransform() {
    }

    public static UserEntity requestToEntity(User request) {
        return new UserEntity(null, request.getEmail(), request.getName(), true);
    }

    public static User entityToResponse(UserEntity userEntity) {
        User user = new User();

        user.setEmail(userEntity.getEmail());
        user.setName(userEntity.getName());

        return user;
    }

    public static void updateEntity(UserEntity tar, UserEntity src) {
        tar.setName(src.getName());
    }

}
