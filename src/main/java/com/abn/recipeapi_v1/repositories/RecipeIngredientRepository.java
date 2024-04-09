package com.abn.recipeapi_v1.repositories;

import com.abn.recipeapi_v1.model.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, UUID> {
    Optional<RecipeIngredient> findByRecipeIdAndIngredientId(UUID recipeId, UUID ingredientId);
    boolean existsByRecipeIdAndIngredientId(UUID recipeId, UUID ingredientId);
}
