package com.abn.recipeapi_v1.dto;

import com.abn.recipeapi_v1.model.Ingredient;
import com.abn.recipeapi_v1.model.RecipeIngredient;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

import java.util.Objects;

public class IngredientDTO {

    private Long id;

    private String name;

    public IngredientDTO() {}

    public IngredientDTO(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
    }

    public IngredientDTO(String name) {
        this.name = name;
    }

    public IngredientDTO(@Valid RecipeIngredient ingredient) {
        this.id = ingredient.getIngredient().getId();
        this.name = ingredient.getIngredient().getName();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientDTO that = (IngredientDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
