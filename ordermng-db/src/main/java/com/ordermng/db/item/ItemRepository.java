package com.ordermng.db.item;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ordermng.core.OrderManagerException;
import com.ordermng.db.order.OrderItemEntity;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, String> {
    @Query("select i from ItemEntity i where i.code = :itemCode and i.active = true")
    Optional<ItemEntity> findActiveByCode(@Param("itemCode") String itemCode);

    @Query("select i from ItemEntity i where i.active = true")
    Iterable<ItemEntity> findAllActive();

    public default void checkItensInOrderItems(List<OrderItemEntity> orderItemsEntity) throws OrderManagerException {
        for(OrderItemEntity orderItemEntity: orderItemsEntity) {
            Optional<ItemEntity> optionalItemEntity = findActiveByCode(orderItemEntity.getItem().getCode());
        
            if(!optionalItemEntity.isPresent()) {
                throw new OrderManagerException(String.format("The order item has an inexistent item: %s", orderItemEntity));
            }
        }
    }
}
