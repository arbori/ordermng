package com.ordermng.db.repository;

import org.springframework.data.repository.CrudRepository;

import com.ordermng.db.OrderEntity;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
}
