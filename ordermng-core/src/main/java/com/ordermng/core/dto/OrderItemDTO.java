package com.ordermng.core.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderItemDTO {
    private ItemDTO item;
    private Double quantity;
    private List<StockMovementDTO> movements;

    public OrderItemDTO() {
    }

    public OrderItemDTO(ItemDTO item, Double quantity) {
        this.item = item;
        this.quantity = quantity;
        this.movements = new ArrayList<>();
    }

    public OrderItemDTO(ItemDTO item, Double quantity, List<StockMovementDTO> movements) {
        this.item = item;
        this.quantity = quantity;
        this.movements = new ArrayList<>();

        if(movements != null && !movements.isEmpty()) {
            this.movements.addAll(movements);
        }
    }

    public OrderItemDTO(OrderItemDTO obj) {
        this.item = obj.item;
        this.quantity = obj.quantity;
        this.movements = new ArrayList<>();

        if(obj.movements != null && !obj.movements.isEmpty()) {
            this.movements.addAll(obj.movements);
        }
    }

    public ItemDTO getItem() {
        return item;
    }
    public void setItem(ItemDTO item) {
        this.item = item;
    }
    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    public List<StockMovementDTO> getMovements() {
        return movements;
    }
    public void setMovements(List<StockMovementDTO> stockMovements) {
        movements.clear();
        movements.addAll(stockMovements);
    }

    @Override
    public String toString() {
        return "OrderItem [item=" + item + ", quantity=" + quantity + ", movements={" + movements + "}]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((movements == null) ? 0 : movements.hashCode());
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
        OrderItemDTO other = (OrderItemDTO) obj;
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
        if (movements == null) {
            if (other.movements != null)
                return false;
        } else if (!movements.equals(other.movements))
            return false;
        return true;
    }
}
