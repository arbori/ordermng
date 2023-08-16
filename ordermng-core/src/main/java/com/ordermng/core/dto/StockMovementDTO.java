package com.ordermng.core.dto;

import java.time.LocalDateTime;

public class StockMovementDTO {
    private ItemDTO item;
    private OrderItemDTO orderItem;
    private LocalDateTime creationDate;
    private Double quantity;
    private Boolean active;

    public StockMovementDTO() {
    }

    public StockMovementDTO(ItemDTO item, OrderItemDTO orderItem, LocalDateTime creationDate, Double quantity, Boolean active) {
        this.item = item;
        this.orderItem = orderItem;
        this.creationDate = creationDate;
        this.quantity = quantity;
        this.active = active;
    }

    public StockMovementDTO(StockMovementDTO movement) {
        this.item = movement.item;
        this.orderItem = movement.orderItem;
        this.creationDate = movement.creationDate;
        this.quantity = movement.quantity;
        this.active = movement.active;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public OrderItemDTO getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItemDTO orderItem) {
        this.orderItem = orderItem;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
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

        StockMovementDTO other = (StockMovementDTO) obj;

        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
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
