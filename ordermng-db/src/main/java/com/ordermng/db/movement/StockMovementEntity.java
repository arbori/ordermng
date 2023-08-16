package com.ordermng.db.movement;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ordermng.db.item.ItemEntity;
import com.ordermng.db.order.OrderItemEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name =  "ordermng_stock_movement")
public class StockMovementEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movement_id")
	private Long id;

	@Column(name = "movement_creation_date")
	private LocalDateTime creationDate;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movement_item_code")
	private ItemEntity itemEntity;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movement_orderitem_id")
    private OrderItemEntity orderItemEntity;

	@Column(name = "movement_quantity")
	private Double quantity;

	@Column(name = "movement_active")
	private Boolean active;
}
