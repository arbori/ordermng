package com.ordermng.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Order
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-07-07T11:52:41.130101+01:00[Europe/Lisbon]")


public class Order   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("creationDate")
  private OffsetDateTime creationDate = null;

  @JsonProperty("item")
  private Object item = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

  @JsonProperty("user")
  private Object user = null;

  @JsonProperty("moviment")
  private Object moviment = null;

  @JsonProperty("active")
  private Boolean active = null;

  public Order id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(example = "29843", description = "")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Order creationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Get creationDate
   * @return creationDate
   **/
  @Schema(example = "2021-10-18T08:32:28Z", description = "")
  
    @Valid
    public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public Order item(Object item) {
    this.item = item;
    return this;
  }

  /**
   * Get item
   * @return item
   **/
  @Schema(description = "")
  
    public Object getItem() {
    return item;
  }

  public void setItem(Object item) {
    this.item = item;
  }

  public Order quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
   **/
  @Schema(example = "42", description = "")
  
    public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Order user(Object user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
   **/
  @Schema(description = "")
  
    public Object getUser() {
    return user;
  }

  public void setUser(Object user) {
    this.user = user;
  }

  public Order moviment(Object moviment) {
    this.moviment = moviment;
    return this;
  }

  /**
   * Get moviment
   * @return moviment
   **/
  @Schema(description = "")
  
    public Object getMoviment() {
    return moviment;
  }

  public void setMoviment(Object moviment) {
    this.moviment = moviment;
  }

  public Order active(Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * Get active
   * @return active
   **/
  @Schema(example = "true", description = "")
  
    public Boolean isActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(this.id, order.id) &&
        Objects.equals(this.creationDate, order.creationDate) &&
        Objects.equals(this.item, order.item) &&
        Objects.equals(this.quantity, order.quantity) &&
        Objects.equals(this.user, order.user) &&
        Objects.equals(this.moviment, order.moviment) &&
        Objects.equals(this.active, order.active);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, creationDate, item, quantity, user, moviment, active);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    item: ").append(toIndentedString(item)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    moviment: ").append(toIndentedString(moviment)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
