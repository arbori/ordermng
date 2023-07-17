package com.ordermng.db.repository;

import org.springframework.data.repository.CrudRepository;

import com.ordermng.db.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    
}
