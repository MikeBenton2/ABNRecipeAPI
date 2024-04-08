package com.abn.recipeapi_v1.model;

import java.net.URI;
import java.util.Objects;
import com.abn.recipeapi_v1.model.Ingredient;
import com.abn.recipeapi_v1.model.Recipe;
import com.abn.recipeapi_v1.model.RecipeIngredientId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * RecipeIngredient
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-27T16:17:05.660628+01:00[Europe/Amsterdam]")
@jakarta.persistence.Entity(name = "RecipeIngredient") @jakarta.persistence.Table(name = "RecipeIngredient")
public class RecipeIngredient implements Serializable {

  private static final long serialVersionUID = 1L;

  @EmbeddedId
  public RecipeIngredientId recipeIngredientId = new RecipeIngredientId();

  @ManyToOne()
  @MapsId("recipeId")
  @JoinColumn(name = "recipeId", referencedColumnName = "id")
  public Recipe recipe;

  @ManyToOne()
  @MapsId("ingredientId")
  @JoinColumn(name = "ingredientId", referencedColumnName = "id")
  public Ingredient ingredient;

  public RecipeIngredient() {}

  public RecipeIngredient (Recipe recipe, Ingredient ingredient) {
    this.recipe = recipe;
    this.ingredient = ingredient;
  }

  /**
   * Get id
   * @return id
  */
  @Valid 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public RecipeIngredientId getId() {
    return recipeIngredientId;
  }

  public void setId(RecipeIngredientId id) {
    this.recipeIngredientId = id;
  }

  public RecipeIngredient recipe(Recipe recipe) {
    this.recipe = recipe;
    return this;
  }

  /**
   * Get recipe
   * @return recipe
  */
  @Valid 
  @Schema(name = "recipe", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("recipe")
  public Recipe getRecipe() {
    return recipe;
  }

  public void setRecipe(Recipe recipe) {
    this.recipe = recipe;
  }

  public RecipeIngredient ingredient(Ingredient ingredient) {
    this.ingredient = ingredient;
    return this;
  }

  /**
   * Get ingredient
   * @return ingredient
  */
  @Valid 
  @Schema(name = "ingredient", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ingredient")
  public Ingredient getIngredient() {
    return ingredient;
  }

  public void setIngredient(Ingredient ingredient) {
    this.ingredient = ingredient;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RecipeIngredient that = (RecipeIngredient) o;
    return recipeIngredientId.equals(that.recipeIngredientId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recipeIngredientId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecipeIngredient {\n");
    sb.append("    id: ").append(toIndentedString(recipeIngredientId)).append("\n");
    sb.append("    recipe: ").append(toIndentedString(recipe)).append("\n");
    sb.append("    ingredient: ").append(toIndentedString(ingredient)).append("\n");
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

