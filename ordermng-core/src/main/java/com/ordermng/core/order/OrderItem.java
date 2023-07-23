package com.ordermng.core.order;

import java.util.ArrayList;
import java.util.List;

import com.ordermng.core.item.Item;
import com.ordermng.core.item.ItemUseCase;
import com.ordermng.core.movement.StockMovement;

public class OrderItem {
    private Item item;
    private Double quantity;
    private List<StockMovement> movements;

    /**
     * An order item may not be fulfilled at the same time the order is entered. The addStockMovement method provides means to associate stock movements that aim to meet the request for an item, contained in an order. 
     * The stock movement received must to be at the maximum the difference between the quantity asked for the item and the sum of the set of stock movement already associated to the OrderItem object. Therefore, any stock movement with the quantity higher then that difference will have its quantity changed to be enogth to fulfiled the order item, or will not be inserted if ordem item is alread satisfied.
     * 
     * @param stockMovement The stock movement that aim to meet the need of an item.
     */
    public void addStockMovement(StockMovement stockMovement) {
        double missing = getQuantity() - getAmountMoviment();
        
        if(missing > 0.0) {
            if(stockMovement.getQuantity() > missing) {
                stockMovement.setQuantity(missing);
            }

            this.movements.add(stockMovement);
        }
    }

    /**
     * Check if the order item object has fulfilled. Its means, the sum of StockMovements' quantities are enough to satisfy the quantity in order item. 
     * @return Return true if object has been satisfied.
     */
    public boolean isSatisfied() {
        double difference = getAmountMoviment() - this.quantity;

        return ItemUseCase.isZero(difference);
    }
    
    /**
     * Computes the sum of stock movements'.
     * @return The total of quantities.
     */
    public double getAmountMoviment() {
        double total = 0.0;

        for(StockMovement stockMovement: this.movements) {
            total += stockMovement.getQuantity();
        }

        return total;
    }
    
    public OrderItem(Item item, Double quantity) {
        this.item = item;
        this.quantity = quantity;
        this.movements = new ArrayList<>();
    }

    public OrderItem(Item item, Double quantity, List<StockMovement> movements) {
        this.item = item;
        this.quantity = quantity;
        this.movements = new ArrayList<>();

        if(movements != null) {
            this.movements.addAll(movements);
        }
    }

    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    public List<StockMovement> getMovements() {
        List<StockMovement> result = new ArrayList<>();
        
        result.addAll(movements);

        return result;
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
        OrderItem other = (OrderItem) obj;
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
