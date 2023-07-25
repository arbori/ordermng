package com.ordermng.db.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ordermng.core.OrderManagerException;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
    @Query("select u from UserEntity u where u.email = :email and u.active = true")
    Optional<UserEntity> findActiveByEmail(@Param("email") String email);

    @Query("select u from UserEntity u where u.active = true")
    Iterable<UserEntity> findAllActive();

    public default UserEntity findAndFillUser(UserEntity userEntity) throws OrderManagerException{
        Optional<UserEntity> optionalUserEntity = findActiveByEmail(userEntity.getEmail());

        if(!optionalUserEntity.isPresent()) {
            throw new OrderManagerException(String.format("User does not exist: %s", userEntity));
        }

        return optionalUserEntity.get();
    }
}
