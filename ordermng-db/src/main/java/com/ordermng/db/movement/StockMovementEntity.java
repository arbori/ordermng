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
import com.ordermng.db.order.OrderItemEntity;

import java.time.LocalDateTime;

import javax.persistence.Column;

@Entity
@Table(name =  "ordermng_stock_movement")
public class StockMovementEntity extends StockMovement {
	public StockMovementEntity() {
		super();
	}
	public StockMovementEntity(Long id, LocalDateTime creationDate, Item item, Double quantity, Boolean active) {
		super(creationDate, item, quantity, active);

		setId(id);
	}
	public StockMovementEntity(StockMovementEntity movement) {
		super(movement);

		setId(movement.getId());
	}

	private Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movement_id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "movement_creation_date")
	@Override
	public LocalDateTime getCreationDate() {
		return super.getCreationDate();
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movement_item_code")
	@Override
	public ItemEntity getItem() {
		return (ItemEntity) super.getItem();
	}
	public void setItem(ItemEntity item) {
		super.setItem(item);
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movement_orderitem_id")
	@Override
	public OrderItemEntity getOrderItem() {
		return (OrderItemEntity) super.getOrderItem();
	}
	public void setOrderItem(OrderItemEntity orderItemEntity) {
		super.setOrderItem(orderItemEntity);
	}

	@Column(name = "movement_quantity")
	@Override
	public Double getQuantity() {
		return super.getQuantity();
	}

	@Column(name = "movement_active")
	@Override
	public Boolean getActive() {
		return super.getActive();
	}

    @ManyToOne
    @JoinColumn(name = "movement_orderitem_id")
    private OrderItemEntity orderItemEntity;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockMovementEntity other = (StockMovementEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
