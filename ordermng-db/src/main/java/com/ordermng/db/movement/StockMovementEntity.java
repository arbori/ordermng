package com.ordermng.db.movement;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ordermng.core.item.Item;
import com.ordermng.core.movement.StockMovement;
import com.ordermng.db.item.ItemEntity;

import java.time.LocalDateTime;

import javax.persistence.Column;

@Entity
@Table(name =  "ordermng_stock_moviment")
public class StockMovementEntity extends StockMovement {
	public StockMovementEntity() {
		super();
	}
	public StockMovementEntity(Long id, LocalDateTime creationDate, Item item, Double quantity, Boolean active) {
		super(id, creationDate, item, quantity, active);
	}
	public StockMovementEntity(StockMovementEntity movement) {
		super(movement);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "moviment_id")
	@Override
	public Long getId() {
		return super.getId();
	}

	@Column(name = "moviment_creation_date")
	@Override
	public LocalDateTime getCreationDate() {
		return super.getCreationDate();
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "moviment_item_code")
	@Override
	public ItemEntity getItem() {
		return (ItemEntity) super.getItem();
	}

	public void setItem(ItemEntity item) {
		super.setItem(item);
	}

	@Column(name = "moviment_quantity")
	@Override
	public Double getQuantity() {
		return super.getQuantity();
	}

	@Column(name = "moviment_active")
	@Override
	public Boolean getActive() {
		return super.getActive();
	}
}
