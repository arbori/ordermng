package com.ordermng.api.transform;

import com.ordermng.db.UserEntity;

public class UserTransform {
    private UserTransform() {
    }

    public static com.ordermng.api.model.User entityToApiModel(UserEntity user) {
        com.ordermng.api.model.User body = new com.ordermng.api.model.User();

        body.setId(user.getId());
        body.setName(user.getName());
        body.setEmail(user.getEmail());
        body.setActive(user.getActive());

        return body;
    }

    public static com.ordermng.core.domine.User apiModelToDomine(com.ordermng.api.model.User body) {
        return new com.ordermng.core.domine.User(
            body.getId(), 
            body.getName(), 
            body.getEmail(),
            body.isActive());
    }

    public static void updateEntity(UserEntity userEntity, com.ordermng.core.domine.User user) {
        userEntity.setName(user.getName());
        userEntity.setActive(user.getActive());
        userEntity.setEmail(user.getEmail());
    }
}
