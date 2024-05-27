package com.abn.recipeapi_v1.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Ingredient
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-24T15:33:53.289340+02:00[Europe/Amsterdam]")
@jakarta.persistence.Entity(name = "Ingredient")
public class Ingredient implements Serializable {

  private static final long serialVersionUID = 1L;

  @jakarta.persistence.Id @jakarta.persistence.GeneratedValue
  private UUID id;

  private String name;

  @jakarta.persistence.OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
  @Valid
  private List<@Valid RecipeIngredient> recipeIngredients;

  public Ingredient() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Ingredient(String name) {
    this.name = name;
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

  public Ingredient recipeIngredients(List<@Valid RecipeIngredient> recipeIngredients) {
    this.recipeIngredients = recipeIngredients;
    return this;
  }

  public Ingredient addRecipeIngredientsItem(RecipeIngredient recipeIngredientsItem) {
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
    Ingredient ingredient = (Ingredient) o;
    return Objects.equals(this.id, ingredient.id) &&
        Objects.equals(this.name, ingredient.name) &&
        Objects.equals(this.recipeIngredients, ingredient.recipeIngredients);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, recipeIngredients);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ingredient {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

