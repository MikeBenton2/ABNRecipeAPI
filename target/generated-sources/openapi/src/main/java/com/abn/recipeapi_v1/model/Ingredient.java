package com.abn.recipeapi_v1.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Ingredient
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-20T12:28:10.528866+01:00[Europe/Amsterdam]")
public class Ingredient implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID id;

  private String name;

  private Boolean required;

  private Boolean included;

  public Ingredient() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Ingredient(String name, Boolean required, Boolean included) {
    this.name = name;
    this.required = required;
    this.included = included;
  }

  public Ingredient id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @Valid 
  @Schema(name = "id", accessMode = Schema.AccessMode.READ_ONLY, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Ingredient name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull @Size(min = 1) 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Ingredient required(Boolean required) {
    this.required = required;
    return this;
  }

  /**
   * Get required
   * @return required
  */
  @NotNull 
  @Schema(name = "required", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("required")
  public Boolean getRequired() {
    return required;
  }

  public void setRequired(Boolean required) {
    this.required = required;
  }

  public Ingredient included(Boolean included) {
    this.included = included;
    return this;
  }

  /**
   * Get included
   * @return included
  */
  @NotNull 
  @Schema(name = "included", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("included")
  public Boolean getIncluded() {
    return included;
  }

  public void setIncluded(Boolean included) {
    this.included = included;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ingredient ingredient = (Ingredient) o;
    return Objects.equals(this.id, ingredient.id) &&
        Objects.equals(this.name, ingredient.name) &&
        Objects.equals(this.required, ingredient.required) &&
        Objects.equals(this.included, ingredient.included);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, required, included);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ingredient {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    required: ").append(toIndentedString(required)).append("\n");
    sb.append("    included: ").append(toIndentedString(included)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

