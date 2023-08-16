package com.ordermng.db.movement;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMovementRepository extends CrudRepository<StockMovementEntity, Long> {
    @Query("select m from StockMovementEntity m where m.id = :id and m.active = true")
    Optional<StockMovementEntity> findActiveById(@Param("id") Long id);

    @Query("select m from StockMovementEntity m where m.active = true")
    Iterable<StockMovementEntity> findAllActive();

    @Query("select sum(m.quantity) as amount from StockMovementEntity m where m.itemEntity.code = :itemCode")
    Optional<Double> computeAmountByItemCode(@Param("itemCode") String itemCode);
}
