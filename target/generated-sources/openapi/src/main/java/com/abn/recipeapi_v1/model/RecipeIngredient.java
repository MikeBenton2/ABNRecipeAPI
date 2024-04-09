package com.abn.recipeapi_v1.model;

import java.net.URI;
import java.util.Objects;
import com.abn.recipeapi_v1.model.Ingredient;
import com.abn.recipeapi_v1.model.Recipe;
import com.abn.recipeapi_v1.model.RecipeIngredientId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-09T11:23:04.561667+02:00[Europe/Amsterdam]")
@jakarta.persistence.Entity(name = "RecipeIngredient") @jakarta.persistence.Table(name = "RecipeIngredient")
public class RecipeIngredient implements Serializable {

  private static final long serialVersionUID = 1L;

  @jakarta.persistence.EmbeddedId
  private RecipeIngredientId id = new RecipeIngredientId();

  @jakarta.persistence.ManyToOne @jakarta.persistence.MapsId("recipeId") @jakarta.persistence.JoinColumn(name = "recipeId", referencedColumnName = "id")
  private Recipe recipe;

  @jakarta.persistence.ManyToOne @jakarta.persistence.MapsId("ingredientId") @jakarta.persistence.JoinColumn(name = "ingredientId", referencedColumnName = "id")
  private Ingredient ingredient;

  public RecipeIngredient() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RecipeIngredient(Recipe recipe, Ingredient ingredient) {
    this.recipe = recipe;
    this.ingredient = ingredient;
  }

  public RecipeIngredient id(RecipeIngredientId id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @Valid 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public RecipeIngredientId getId() {
    return id;
  }

  public void setId(RecipeIngredientId id) {
    this.id = id;
  }

  public RecipeIngredient recipe(Recipe recipe) {
    this.recipe = recipe;
    return this;
  }

  /**
   * Get recipe
   * @return recipe
  */
  @NotNull @Valid 
  @Schema(name = "recipe", requiredMode = Schema.RequiredMode.REQUIRED)
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
  @NotNull @Valid 
  @Schema(name = "ingredient", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ingredient")
  public Ingredient getIngredient() {
    return ingredient;
  }

  public void setIngredient(Ingredient ingredient) {
    this.ingredient = ingredient;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecipeIngredient recipeIngredient = (RecipeIngredient) o;
    return Objects.equals(this.id, recipeIngredient.id) &&
        Objects.equals(this.recipe, recipeIngredient.recipe) &&
        Objects.equals(this.ingredient, recipeIngredient.ingredient);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, recipe, ingredient);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecipeIngredient {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

