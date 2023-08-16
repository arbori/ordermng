package com.ordermng.core.user;

import java.util.Optional;

import com.ordermng.core.dto.UserDTO;

public interface UserBusiness {
    /**
     * Add a new user in the user repository with the restriction that user's email and name could not exist in repository.
     * 
     * @param user The new user asked to be added in repository.
     * @return Return the user's identification for this user. If the user is realy new, the same identification sent is retrieved. But, if an user with the same email and name already exist, the previous identification will be returned.
     */
    public String addNewUser(UserDTO user);

    /**
     * This method looks for an user with the provided email.
     * @param email The user identify that is looking for.
     * @return The optional for the user object.
     */
    public Optional<UserDTO> findUserByEmail(String email);

    /**
     * It is not supposed to remove an item from database. Instead, an item is turned inactive and cannot be used in any order anymore.
     * @param code The code of an item to be inactive.
     * @return Return the code of the inactivated item.
     */
    public Optional<String> inactiveUser(String code);
    
    static final int EMAIL_SIZE = 32;
    static final int NAME_SIZE = 40;

    public default boolean isValid(UserDTO user) {
        return 
            user.getEmail() != null && !user.getEmail().isEmpty() && user.getEmail().length() <= EMAIL_SIZE &&
            user.getName() != null && !user.getName().isEmpty() && user.getName().length() <= NAME_SIZE &&
            user.getActive() != null;
    }
}
