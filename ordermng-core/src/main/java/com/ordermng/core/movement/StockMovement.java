package com.ordermng.core.movement;

import java.time.LocalDateTime;

import com.ordermng.core.item.Item;
import com.ordermng.core.item.ItemUseCase;
import com.ordermng.core.order.OrderItem;
import com.ordermng.core.order.OrderUseCase;

public class StockMovement {
    private LocalDateTime creationDate;
    private Item item;
    private OrderItem orderItem;
    private Double quantity;
    private Boolean active;

	boolean isValid() {
		return creationDate != null &&
            ItemUseCase.isValid(item) &&
            OrderUseCase.isValid(orderItem) &&
			quantity != null && quantity > 0.0 &&
			active != null;
	}

    public StockMovement() {
    }

    public StockMovement(LocalDateTime creationDate, Item item, Double quantity, Boolean active) {
        this.creationDate = creationDate;
        this.item = item;
        this.quantity = quantity;
        this.active = active;
    }

    public StockMovement(StockMovement movement) {
        this.creationDate = movement.creationDate;
        this.item = movement.item;
        this.quantity = movement.quantity;
        this.active = movement.active;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }
    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("StockMovement [creationDate=").append(creationDate).append(", item=").append(item).append(", quantity=").append(quantity).append(", active=").append(active).append("]").toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((active == null) ? 0 : active.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StockMovement other = (StockMovement) obj;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (active == null) {
            if (other.active != null)
                return false;
        } else if (!active.equals(other.active))
            return false;
        return true;
    }
}
