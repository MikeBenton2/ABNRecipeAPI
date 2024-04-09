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
 * RecipeIngredientId
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-09T11:23:04.561667+02:00[Europe/Amsterdam]")
@jakarta.persistence.Embeddable
public class RecipeIngredientId implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID recipeId;

  private UUID ingredientId;

  public RecipeIngredientId recipeId(UUID recipeId) {
    this.recipeId = recipeId;
    return this;
  }

  /**
   * Get recipeId
   * @return recipeId
  */
  @Valid 
  @Schema(name = "recipeId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("recipeId")
  public UUID getRecipeId() {
    return recipeId;
  }

  public void setRecipeId(UUID recipeId) {
    this.recipeId = recipeId;
  }

  public RecipeIngredientId ingredientId(UUID ingredientId) {
    this.ingredientId = ingredientId;
    return this;
  }

  /**
   * Get ingredientId
   * @return ingredientId
  */
  @Valid 
  @Schema(name = "ingredientId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ingredientId")
  public UUID getIngredientId() {
    return ingredientId;
  }

  public void setIngredientId(UUID ingredientId) {
    this.ingredientId = ingredientId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecipeIngredientId recipeIngredientId = (RecipeIngredientId) o;
    return Objects.equals(this.recipeId, recipeIngredientId.recipeId) &&
        Objects.equals(this.ingredientId, recipeIngredientId.ingredientId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recipeId, ingredientId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecipeIngredientId {\n");
    sb.append("    recipeId: ").append(toIndentedString(recipeId)).append("\n");
    sb.append("    ingredientId: ").append(toIndentedString(ingredientId)).append("\n");
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

