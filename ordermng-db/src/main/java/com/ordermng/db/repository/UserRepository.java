package com.ordermng.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ordermng.db.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
}
