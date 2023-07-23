package com.ordermng.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * Item
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-07-23T12:06:14.117462+01:00[Europe/Lisbon]")


public class Item   {
  @JsonProperty("code")
  private String code = null;

  @JsonProperty("name")
  private String name = null;

  public Item code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
   **/
  @Schema(example = "MAT4526-R", description = "")
  
    public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Item name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(example = "Hamer", description = "")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return Objects.equals(this.code, item.code) &&
        Objects.equals(this.name, item.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Item {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
