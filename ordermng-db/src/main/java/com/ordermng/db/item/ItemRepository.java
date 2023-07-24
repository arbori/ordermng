package com.ordermng.db.item;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, String> {
    @Query("select i from ItemEntity i where i.getCode() = :itemCode and i.getActive() = true")
    Optional<ItemEntity> findActiveByCode(@Param("itemCode") String itemCode);

    @Query("select i from ItemEntity i where i.getActive() = true")
    Iterable<ItemEntity> findAllActive();
}
