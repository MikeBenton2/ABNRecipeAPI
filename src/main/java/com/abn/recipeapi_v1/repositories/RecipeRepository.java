package com.abn.recipeapi_v1.repositories;

import com.abn.recipeapi_v1.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
}