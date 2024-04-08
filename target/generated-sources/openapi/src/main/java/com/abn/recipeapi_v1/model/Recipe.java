package com.abn.recipeapi_v1.model;

import java.net.URI;
import java.util.Objects;
import com.abn.recipeapi_v1.model.Ingredient;
import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Recipe
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-27T16:17:05.660628+01:00[Europe/Amsterdam]")
@jakarta.persistence.Entity(name = "Recipe")
public class Recipe implements Serializable {

  private static final long serialVersionUID = 1L;

  @jakarta.persistence.Id @jakarta.persistence.SequenceGenerator ( name = "recipe_sequence", sequenceName = "recipe_sequence", allocationSize = 1 ) @jakarta.persistence.GeneratedValue ( strategy = SEQUENCE, generator = "recipe_sequence" )
  private Long id;

  private String name;

  private String instructions;

  private Boolean isVegetarian;

  private Integer numberOfServings;

  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
  @Valid
  public List<RecipeIngredient> recipeIngredients = new ArrayList<>();

  public Recipe() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Recipe(String name, String instructions, Boolean isVegetarian, Integer numberOfServings, List<@Valid RecipeIngredient> ingredients) {
    this.name = name;
    this.instructions = instructions;
    this.isVegetarian = isVegetarian;
    this.numberOfServings = numberOfServings;
    this.recipeIngredients = ingredients;
  }

  public Recipe id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", accessMode = Schema.AccessMode.READ_ONLY, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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
  @Schema(name = "numberOfServings", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("numberOfServings")
  public Integer getNumberOfServings() {
    return numberOfServings;
  }

  public void setNumberOfServings(Integer numberOfServings) {
    this.numberOfServings = numberOfServings;
  }

  public Recipe ingredients(List<@Valid RecipeIngredient> ingredients) {
    this.recipeIngredients = ingredients;
    return this;
  }

  public Recipe addIngredientsItem(RecipeIngredient ingredientsItem) {
    if (this.recipeIngredients == null) {
      this.recipeIngredients = new ArrayList<>();
    }
    this.recipeIngredients.add(ingredientsItem);
    return this;
  }

  /**
   * Get recipeIngredients
   * @return recipeIngredients
  */
//  @NotNull @Valid @Size(min = 1, max = 100)
  @Schema(name = "ingredients", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ingredients")
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
    sb.append("    ingredients: ").append(toIndentedString(recipeIngredients)).append("\n");
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

