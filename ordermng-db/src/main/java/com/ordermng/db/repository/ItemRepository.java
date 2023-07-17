package com.ordermng.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ordermng.db.ItemEntity;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Long> {
    
}
