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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Recipe
 */

@Setter
@NoArgsConstructor
@Builder
@jakarta.persistence.Entity(name = "Recipe")
public class Recipe implements Serializable {

  @Serial
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

  public Recipe(
		  UUID id,
		  String name,
		  String instructions,
		  Boolean isVegetarian,
		  Integer numberOfServings,
		  List<RecipeIngredient> recipeIngredients) {
	  this.id = id;
	  this.name = name;
	  this.instructions = instructions;
	  this.isVegetarian = isVegetarian;
	  this.numberOfServings = numberOfServings;
	  this.recipeIngredients = recipeIngredients;
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

  public void addRecipeIngredientsItem(RecipeIngredient recipeIngredientsItem) {
    if (this.recipeIngredients == null) {
      this.recipeIngredients = new ArrayList<>();
    }
    this.recipeIngredients.add(recipeIngredientsItem);
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
	  return "class Recipe {\n" +
			  "    id: " + toIndentedString(id) + "\n" +
			  "    name: " + toIndentedString(name) + "\n" +
			  "    instructions: " + toIndentedString(instructions) + "\n" +
			  "    isVegetarian: " + toIndentedString(isVegetarian) + "\n" +
			  "    numberOfServings: " + toIndentedString(numberOfServings) + "\n" +
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

