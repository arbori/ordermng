package com.ordermng.api.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

@Validated
public class OrderItemRequest   {
  @JsonProperty("item")
  private ItemRequest item;
  @JsonProperty("quantity")
  private Double quantity;

  public OrderItemRequest item(ItemRequest item) {
    this.item = item;
    return this;
  }
  @Schema(example = "", description = "")
  public ItemRequest getItem() {
    return item;
  }
  public void setItem(ItemRequest item) {
    this.item = item;
  }

  public OrderItemRequest quantity(Double quantity) {
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

  @Override
  public String toString() {
    return "OrderItem [item=" + item + ", quantity=" + quantity + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((item == null) ? 0 : item.hashCode());
    result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
      
    OrderItemRequest other = (OrderItemRequest) obj;

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

    return true;
  }
}
