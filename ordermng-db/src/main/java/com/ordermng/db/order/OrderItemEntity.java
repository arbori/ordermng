package com.ordermng.db.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ordermng.core.order.OrderItem;
import com.ordermng.db.item.ItemEntity;
import com.ordermng.db.movement.StockMovementEntity;

@Entity
@Table(name =  "ordermng_order_item")
public class OrderItemEntity extends OrderItem {
    public OrderItemEntity() {
        super();
    }

    public OrderItemEntity(ItemEntity item, Double quantity) {
        super(item, quantity);
    }

    public OrderItemEntity(ItemEntity item, Double quantity, List<StockMovementEntity> movements) {
        super(item, quantity);

        movements.forEach(super::addStockMovement);
    }

   	@Id
	@Column(name = "orderitem_id")
	private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderitem_item_code")
    public ItemEntity getItemEntity() {
        return (ItemEntity) super.getItem();
    }
    public void setItemEntity(ItemEntity itemEntity) {
        super.setItem(itemEntity);
    }

	@Column(name = "orderitem_quantity")
    @Override
    public Double getQuantity() {
        return super.getQuantity();
    }
    
    public void addStockMovement(StockMovementEntity entity) {
        super.addStockMovement(entity);
    }
    public StockMovementEntity getStockMovement(int index) {
        return (StockMovementEntity) super.getMovements().get(index);
    }

	@OneToMany(mappedBy = "OrderItemEntity", fetch = FetchType.EAGER)
    public List<StockMovementEntity> getMovementEntities() {
        List<StockMovementEntity> result = new ArrayList<>();
        super.getMovements().forEach(m -> result.add((StockMovementEntity) m));
        return result;
    }
    public void setMovementEntities(List<StockMovementEntity> movements) {
        super.getMovements().clear();
        movements.forEach(super::addStockMovement);
    }

    @ManyToOne
    @JoinColumn(name = "orderitem_order_id")
    private OrderEntity orderEntity;

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
        OrderItemEntity other = (OrderItemEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
