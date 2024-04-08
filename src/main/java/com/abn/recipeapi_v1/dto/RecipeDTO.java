package com.abn.recipeapi_v1.dto;

import com.abn.recipeapi_v1.model.Recipe;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RecipeDTO {
    private Long id;
    private String name;
    private String instructions;
    private Boolean isVegetarian;
    private Integer numberOfServings;
    private List<IngredientDTO> ingredients;

    public RecipeDTO() {}

    public RecipeDTO(Recipe recipe, List<IngredientDTO> ingredients) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.instructions = recipe.getInstructions();
        this.isVegetarian = recipe.getIsVegetarian();
        this.numberOfServings = recipe.getNumberOfServings();
        this.ingredients = ingredients;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("instructions")
    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @JsonProperty("isVegetarian")
    public Boolean getVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(Boolean isVegetarian) {
        this.isVegetarian = isVegetarian;
    }

    @JsonProperty("numberOfServings")
    public Integer getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(Integer numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    @JsonProperty("ingredients")
    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
}
