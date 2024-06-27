package com.abn.recipeapi_v1.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * RecipeIngredientId
 */

@Setter
@NoArgsConstructor
@jakarta.persistence.Embeddable
public class RecipeIngredientId implements Serializable {

  @Serial
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
	  return "class RecipeIngredientId {\n" +
			  "    recipeId: " + toIndentedString(recipeId) + "\n" +
			  "    ingredientId: " + toIndentedString(ingredientId) + "\n" +
			  "}";
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

