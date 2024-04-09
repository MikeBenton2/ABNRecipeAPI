package com.abn.recipeapi_v1.model;

import java.net.URI;
import java.util.Objects;
import com.abn.recipeapi_v1.model.RecipeIngredient;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Recipe
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-09T11:23:04.561667+02:00[Europe/Amsterdam]")
@jakarta.persistence.Entity(name = "Recipe")
public class Recipe implements Serializable {

  private static final long serialVersionUID = 1L;

  @jakarta.persistence.Id @jakarta.persistence.GeneratedValue
  private UUID id;

  private String name;

  private String instructions;

  private Boolean isVegetarian;

  private Integer numberOfServings;

  @jakarta.persistence.OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
  @Valid
  private List<@Valid RecipeIngredient> recipeIngredients;

  public Recipe() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Recipe(String name, String instructions, Boolean isVegetarian, Integer numberOfServings) {
    this.name = name;
    this.instructions = instructions;
    this.isVegetarian = isVegetarian;
    this.numberOfServings = numberOfServings;
  }

  public Recipe id(UUID id) {
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

  public Recipe name(String name) {
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

  public Recipe instructions(String instructions) {
    this.instructions = instructions;
    return this;
  }

  /**
   * Get instructions
   * @return instructions
  */
  @NotNull @Size(min = 1) 
  @Schema(name = "instructions", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("instructions")
  public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public Recipe isVegetarian(Boolean isVegetarian) {
    this.isVegetarian = isVegetarian;
    return this;
  }

  /**
   * Get isVegetarian
   * @return isVegetarian
  */
  @NotNull 
  @Schema(name = "isVegetarian", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("isVegetarian")
  public Boolean getIsVegetarian() {
    return isVegetarian;
  }

  public void setIsVegetarian(Boolean isVegetarian) {
    this.isVegetarian = isVegetarian;
  }

  public Recipe numberOfServings(Integer numberOfServings) {
    this.numberOfServings = numberOfServings;
    return this;
  }

  /**
   * Get numberOfServings
   * @return numberOfServings
  */
  @NotNull 
  @Schema(name = "numberOfServings", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("numberOfServings")
  public Integer getNumberOfServings() {
    return numberOfServings;
  }

  public void setNumberOfServings(Integer numberOfServings) {
    this.numberOfServings = numberOfServings;
  }

  public Recipe recipeIngredients(List<@Valid RecipeIngredient> recipeIngredients) {
    this.recipeIngredients = recipeIngredients;
    return this;
  }

  public Recipe addRecipeIngredientsItem(RecipeIngredient recipeIngredientsItem) {
    if (this.recipeIngredients == null) {
      this.recipeIngredients = new ArrayList<>();
    }
    this.recipeIngredients.add(recipeIngredientsItem);
    return this;
  }

  /**
   * Get recipeIngredients
   * @return recipeIngredients
  */
  @Valid 
  @Schema(name = "recipeIngredients", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("recipeIngredients")
  public List<@Valid RecipeIngredient> getRecipeIngredients() {
    return recipeIngredients;
  }

  public void setRecipeIngredients(List<@Valid RecipeIngredient> recipeIngredients) {
    this.recipeIngredients = recipeIngredients;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Recipe recipe = (Recipe) o;
    return Objects.equals(this.id, recipe.id) &&
        Objects.equals(this.name, recipe.name) &&
        Objects.equals(this.instructions, recipe.instructions) &&
        Objects.equals(this.isVegetarian, recipe.isVegetarian) &&
        Objects.equals(this.numberOfServings, recipe.numberOfServings) &&
        Objects.equals(this.recipeIngredients, recipe.recipeIngredients);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, instructions, isVegetarian, numberOfServings, recipeIngredients);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Recipe {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    instructions: ").append(toIndentedString(instructions)).append("\n");
    sb.append("    isVegetarian: ").append(toIndentedString(isVegetarian)).append("\n");
    sb.append("    numberOfServings: ").append(toIndentedString(numberOfServings)).append("\n");
    sb.append("    recipeIngredients: ").append(toIndentedString(recipeIngredients)).append("\n");
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

