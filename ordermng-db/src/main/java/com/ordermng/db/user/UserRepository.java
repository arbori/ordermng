package com.ordermng.db.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
    @Query("select u from UserEntity u where u.getEmail() = :email and u.getActive() = true")
    Optional<UserEntity> findActiveByEmail(@Param("email") String email);

    @Query("select u from UserEntity u where u.getActive() = true")
    Iterable<UserEntity> findAllActive();
}
