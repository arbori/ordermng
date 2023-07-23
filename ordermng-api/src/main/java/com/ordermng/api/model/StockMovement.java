package com.ordermng.api.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * Item
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-07-23T12:06:14.117462+01:00[Europe/Lisbon]")
public class StockMovement {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("creationDate")
  private LocalDateTime creationDate;

  @JsonProperty("item")
  private Item item;

  @JsonProperty("quantity")
  private Double quantity;

  public StockMovement id(Long id) {
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

  public StockMovement creationDate(LocalDateTime creationDate) {
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

  public StockMovement item(Item item) {
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

  public StockMovement quantity(Double quantity) {
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
}
