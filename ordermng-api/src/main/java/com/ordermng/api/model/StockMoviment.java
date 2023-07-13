package com.ordermng.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * StockMoviment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-07-07T11:52:41.130101+01:00[Europe/Lisbon]")


public class StockMoviment   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("creationDate")
  private OffsetDateTime creationDate = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

  @JsonProperty("active")
  private Boolean active = null;

  public StockMoviment id(Long id) {
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

  public StockMoviment creationDate(OffsetDateTime creationDate) {
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

  public StockMoviment quantity(Integer quantity) {
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

  public StockMoviment active(Boolean active) {
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
    StockMoviment stockMoviment = (StockMoviment) o;
    return Objects.equals(this.id, stockMoviment.id) &&
        Objects.equals(this.creationDate, stockMoviment.creationDate) &&
        Objects.equals(this.quantity, stockMoviment.quantity) &&
        Objects.equals(this.active, stockMoviment.active);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, creationDate, quantity, active);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StockMoviment {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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
