package com.abn.recipeapi_v1.model;

import java.net.URI;
import java.util.Objects;
import com.abn.recipeapi_v1.model.IngredientDTO;
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
 * RecipeDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-09T11:23:04.561667+02:00[Europe/Amsterdam]")
public class RecipeDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID id;

  private String name;

  private String instructions;

  private Boolean isVegetarian;

  private Integer numberOfServings;

  @Valid
  private List<@Valid IngredientDTO> ingredients = new ArrayList<>();

  public RecipeDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RecipeDTO(String name, String instructions, Boolean isVegetarian, Integer numberOfServings, List<@Valid IngredientDTO> ingredients) {
    this.name = name;
    this.instructions = instructions;
    this.isVegetarian = isVegetarian;
    this.numberOfServings = numberOfServings;
    this.ingredients = ingredients;
  }

  public RecipeDTO id(UUID id) {
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

  public RecipeDTO name(String name) {
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

  public RecipeDTO instructions(String instructions) {
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

  public RecipeDTO isVegetarian(Boolean isVegetarian) {
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

  public RecipeDTO numberOfServings(Integer numberOfServings) {
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

  public RecipeDTO ingredients(List<@Valid IngredientDTO> ingredients) {
    this.ingredients = ingredients;
    return this;
  }

  public RecipeDTO addIngredientsItem(IngredientDTO ingredientsItem) {
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
  @NotNull @Valid @Size(min = 1, max = 100) 
  @Schema(name = "ingredients", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ingredients")
  public List<@Valid IngredientDTO> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<@Valid IngredientDTO> ingredients) {
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
    RecipeDTO recipeDTO = (RecipeDTO) o;
    return Objects.equals(this.id, recipeDTO.id) &&
        Objects.equals(this.name, recipeDTO.name) &&
        Objects.equals(this.instructions, recipeDTO.instructions) &&
        Objects.equals(this.isVegetarian, recipeDTO.isVegetarian) &&
        Objects.equals(this.numberOfServings, recipeDTO.numberOfServings) &&
        Objects.equals(this.ingredients, recipeDTO.ingredients);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, instructions, isVegetarian, numberOfServings, ingredients);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecipeDTO {\n");
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

