package com.ordermng.db.order;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    @Query("select o from OrderEntity o where o.getId() = :id and m.getActive() = true")
    Optional<OrderEntity> findActiveById(@Param("id") Long id);

    @Query("select o from OrderEntity o where o.getActive() = true")
    Iterable<OrderEntity> findAllActive();
}

