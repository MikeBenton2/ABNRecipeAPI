package com.abn.recipeapi_v1.model;

import java.net.URI;
import java.util.Objects;
import com.abn.recipeapi_v1.model.Recipe;
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
 * Ingredient
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-27T16:17:05.660628+01:00[Europe/Amsterdam]")
@jakarta.persistence.Entity(name = "Ingredient")
public class Ingredient implements Serializable {

  private static final long serialVersionUID = 1L;

  @jakarta.persistence.Id @jakarta.persistence.SequenceGenerator ( name = "ingredient_sequence", sequenceName = "ingredient_sequence") @jakarta.persistence.GeneratedValue ( strategy = SEQUENCE, generator = "ingredient_sequence" )
  private Long id;

  private String name;

  @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
  public Set<RecipeIngredient> recipeIngredients = new HashSet<>();

  public Ingredient() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Ingredient(String name) {
    this.name = name;
  }

  public Ingredient id(Long id) {
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

  /**
   * Get recipes
   * @return recipes
  */
  @Valid 
  @Schema(name = "recipes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("recipes")
  public Set<@Valid RecipeIngredient> getRecipes() {
    return recipeIngredients;
  }

  public void setRecipes(Set<@Valid RecipeIngredient> recipes) {
    this.recipeIngredients = recipes;
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
    sb.append("    recipes: ").append(toIndentedString(recipeIngredients)).append("\n");
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

