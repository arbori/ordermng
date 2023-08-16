package com.ordermng.db.order;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItemEntity, Long> {
    @Query("SELECT oi FROM OrderItemEntity oi WHERE oi.quantity > (SELECT COALESCE(SUM(sm.quantity), 0) FROM StockMovementEntity sm WHERE sm.orderItemEntity = oi)")
    public List<OrderItemEntity> findUnsatisfiedOrderItemsByItemCode(@Param("itemCode") String itemCode);
}
