package com.ordermng.api.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

@Validated
public class OrderRequest   {
  @JsonProperty("id")
  private Long id;
  @JsonProperty("creationDate")
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
  private LocalDateTime creationDate;
  @JsonProperty("user")
  private UserRequest user;
  @JsonProperty("orderItems")
  private List<OrderItemRequest> orderItems;
  @JsonProperty("type")
  private String type;
  @JsonProperty("shipped")
  private Boolean shipped;
  @JsonProperty("active")
  private Boolean active;

  public OrderRequest id(Long id) {
    this.id = id;
    return this;
  }
  @Schema(example = "", description = "")
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public OrderRequest creationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }
  @Schema(example = "", description = "")
  public LocalDateTime getCreationDate() {
    return creationDate;
  }
  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public OrderRequest user(UserRequest user) {
    this.user = user;
    return this;
  }
  @Schema(example = "", description = "")
  public UserRequest getUser() {
    return user;
  }
  public void setUser(UserRequest user) {
    this.user = user;
  }

  public OrderRequest orderItems(List<OrderItemRequest> orderItems) {
    this.orderItems = orderItems;
    return this;
  }
  @Schema(example = "", description = "")
  public List<OrderItemRequest> getOrderItems() {
    return orderItems;
  }
  public void setOrderItems(List<OrderItemRequest> orderItems) {
    this.orderItems = orderItems;
  }

  public OrderRequest type(String type) {
    this.type = type;
    return this;
  }
  @Schema(example = "", description = "")
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  public OrderRequest shipped(Boolean shipped) {
    this.shipped = shipped;
    return this;
  }
  @Schema(example = "", description = "")
  public Boolean getShipped() {
    return shipped;
  }
  public void setShipped(Boolean shipped) {
    this.shipped = shipped;
  }

  public OrderRequest active(Boolean active) {
    this.active = active;
    return this;
  }
  @Schema(example = "", description = "")
  public Boolean getActive() {
    return active;
  }
  public void setActive(Boolean active) {
    this.active = active;
  }

  @Override
  public String toString() {
    return "Order [id=" + id + ", creationDate=" + creationDate + ", user=" + user + ", orderItems=" + orderItems
        + ", active=" + active + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
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
    OrderRequest other = (OrderRequest) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (creationDate == null) {
      if (other.creationDate != null)
        return false;
    } else if (!creationDate.equals(other.creationDate))
      return false;
    if (user == null) {
      if (other.user != null)
        return false;
    } else if (!user.equals(other.user))
      return false;
    if (orderItems == null) {
      if (other.orderItems != null)
        return false;
    } else if (!orderItems.equals(other.orderItems))
      return false;
    if (active == null) {
      if (other.active != null)
        return false;
    } else if (!active.equals(other.active))
      return false;
    return true;
  }

}
