package com.abn.recipeapi_v1.repositories;

import com.abn.recipeapi_v1.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID>, JpaSpecificationExecutor<Ingredient> {
    Ingredient findIngredientByName(String name);
    boolean existsByNameIgnoreCase(String name);
}
