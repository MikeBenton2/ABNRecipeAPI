package com.abn.recipeapi_v1.model;

import java.net.URI;
import java.util.Objects;
import com.abn.recipeapi_v1.model.Ingredient;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
 * RecipeProperties
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-20T12:28:10.528866+01:00[Europe/Amsterdam]")
public class RecipeProperties implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID id;

  private String name;

  private String instructions;

  private Boolean isVegetarian;

  private String numberOfServings;

  @Valid
  private List<@Valid Ingredient> ingredients;

  public RecipeProperties id(UUID id) {
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

  public RecipeProperties name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @Size(min = 1) 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RecipeProperties instructions(String instructions) {
    this.instructions = instructions;
    return this;
  }

  /**
   * Get instructions
   * @return instructions
  */
  @Size(min = 1) 
  @Schema(name = "instructions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("instructions")
  public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public RecipeProperties isVegetarian(Boolean isVegetarian) {
    this.isVegetarian = isVegetarian;
    return this;
  }

  /**
   * Get isVegetarian
   * @return isVegetarian
  */
  
  @Schema(name = "isVegetarian", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isVegetarian")
  public Boolean getIsVegetarian() {
    return isVegetarian;
  }

  public void setIsVegetarian(Boolean isVegetarian) {
    this.isVegetarian = isVegetarian;
  }

  public RecipeProperties numberOfServings(String numberOfServings) {
    this.numberOfServings = numberOfServings;
    return this;
  }

  /**
   * Get numberOfServings
   * @return numberOfServings
  */
  @Pattern(regexp = "^[0-9]*$") 
  @Schema(name = "numberOfServings", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numberOfServings")
  public String getNumberOfServings() {
    return numberOfServings;
  }

  public void setNumberOfServings(String numberOfServings) {
    this.numberOfServings = numberOfServings;
  }

  public RecipeProperties ingredients(List<@Valid Ingredient> ingredients) {
    this.ingredients = ingredients;
    return this;
  }

  public RecipeProperties addIngredientsItem(Ingredient ingredientsItem) {
    if (this.ingredients == null) {
      this.ingredients = new ArrayList<>();
    }
    this.ingredients.add(ingredientsItem);
    return this;
  }

  /**
   * Get ingredients
   * @return ingredients
  */
  @Valid @Size(min = 1, max = 100) 
  @Schema(name = "ingredients", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ingredients")
  public List<@Valid Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<@Valid Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecipeProperties recipeProperties = (RecipeProperties) o;
    return Objects.equals(this.id, recipeProperties.id) &&
        Objects.equals(this.name, recipeProperties.name) &&
        Objects.equals(this.instructions, recipeProperties.instructions) &&
        Objects.equals(this.isVegetarian, recipeProperties.isVegetarian) &&
        Objects.equals(this.numberOfServings, recipeProperties.numberOfServings) &&
        Objects.equals(this.ingredients, recipeProperties.ingredients);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, instructions, isVegetarian, numberOfServings, ingredients);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecipeProperties {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    instructions: ").append(toIndentedString(instructions)).append("\n");
    sb.append("    isVegetarian: ").append(toIndentedString(isVegetarian)).append("\n");
    sb.append("    numberOfServings: ").append(toIndentedString(numberOfServings)).append("\n");
    sb.append("    ingredients: ").append(toIndentedString(ingredients)).append("\n");
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

