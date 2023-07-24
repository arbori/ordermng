package com.ordermng.api.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

@Validated
public class OrderItem   {
  @JsonProperty("item")
  private Item item;
  @JsonProperty("quantity")
  private Double quantity;
  @JsonProperty("movements")
  private List<StockMovement> movements;

  public OrderItem item(Item item) {
    this.item = item;
    return this;
  }
  @Schema(example = "", description = "")
  public Item getItem() {
    return item;
  }
  public void setItem(Item item) {
    this.item = item;
  }

  public OrderItem quantity(Double quantity) {
    this.quantity = quantity;
    return this;
  }
  @Schema(example = "", description = "")
  public Double getQuantity() {
    return quantity;
  }
  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public OrderItem movements(List<StockMovement> movements) {
    this.movements = movements;
    return this;
  }
  @Schema(example = "", description = "")
  public List<StockMovement> getMovements() {
    return movements;
  }
  public void setMovements(List<StockMovement> movements) {
    this.movements = movements;
  }

  @Override
  public String toString() {
    return "OrderItem [item=" + item + ", quantity=" + quantity + ", movements=" + movements + "]";
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
