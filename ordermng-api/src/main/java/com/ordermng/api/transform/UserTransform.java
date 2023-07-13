package com.ordermng.api.transform;

public class UserTransform {
    private UserTransform() {
    }

    public static com.ordermng.api.model.User model2api(com.ordermng.db.User user) {
        com.ordermng.api.model.User body = new com.ordermng.api.model.User();

        body.setId(user.getId());
        body.setName(user.getName());
        body.setEmail(user.getEmail());
        body.setActive(user.getActive());

        return body;
    }

    public static com.ordermng.db.User api2model(com.ordermng.api.model.User body) {
        com.ordermng.db.User user = new com.ordermng.db.User();

        user.setId(body.getId());
        user.setName(body.getName());
        user.setEmail(body.getEmail());
        user.setActive(body.isActive());

        return user;
    }
}
