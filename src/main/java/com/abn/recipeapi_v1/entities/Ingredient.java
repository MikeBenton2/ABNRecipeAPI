package com.abn.recipeapi_v1.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Ingredient
 */

@Setter
@NoArgsConstructor
@Builder
@jakarta.persistence.Entity(name = "Ingredient")
public class Ingredient implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @jakarta.persistence.Id @jakarta.persistence.GeneratedValue
  private UUID id;

  private String name;

  @jakarta.persistence.OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
  @Valid
  private List<@Valid RecipeIngredient> recipeIngredients;

  public Ingredient(String name) {
	  this.name = name;
  }

  public Ingredient(UUID id, String name, List<RecipeIngredient> recipeIngredients) {
	  this.id = id;
	  this.name = name;
	  this.recipeIngredients = recipeIngredients;
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
	  return "class Ingredient {\n" +
			  "    id: " + toIndentedString(id) + "\n" +
			  "    name: " + toIndentedString(name) + "\n" +
			  "    recipeIngredients: " + toIndentedString(recipeIngredients) + "\n" +
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

