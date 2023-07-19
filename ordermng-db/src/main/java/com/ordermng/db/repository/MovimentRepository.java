package com.ordermng.db.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovimentRepository {
    @Query("select sum(m.movimentQuantity) from StockMovimentEntity m where m.id = :itemId")
    Long computeItemStock(@Param("itemId") Long itemId);
}
