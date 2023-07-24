package com.ordermng.db.movement;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMovementRepository extends CrudRepository<StockMovementEntity, Long> {
    @Query("select m from StockMovementEntity m where m.getId() = :id and m.getActive() = true")
    Optional<StockMovementEntity> findActiveById(@Param("id") Long id);

    @Query("select m from StockMovementEntity m where m.active = true")
    Iterable<StockMovementEntity> findAllActive();
}
