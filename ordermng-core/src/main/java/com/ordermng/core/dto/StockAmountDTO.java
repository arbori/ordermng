package com.ordermng.core.dto;

public class StockAmountDTO {
    private ItemDTO item;
    private Double amount;

    public StockAmountDTO() {
    }

    public StockAmountDTO(ItemDTO item, Double amount) {
        this.item = item;
        this.amount = amount;
    }

    public StockAmountDTO(StockAmountDTO obj) {
        this.item = obj.item;
        this.amount = obj.amount;
    }
    
    public ItemDTO getItem() {
        return item;
    }
    public void setItem(ItemDTO item) {
        this.item = item;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    @Override
    public String toString() {
        return new StringBuilder().append("StockAmount [item=").append(item).append(", amount=").append(amount).append("]").toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
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
        StockAmountDTO other = (StockAmountDTO) obj;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        return true;
    }
}
