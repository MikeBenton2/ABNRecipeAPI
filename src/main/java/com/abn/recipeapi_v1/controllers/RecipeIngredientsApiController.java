package com.abn.recipeapi_v1.controllers;

import com.abn.recipeapi_v1.RecipeIngredientsApi;
import com.abn.recipeapi_v1.model.RecipeDTO;
import com.abn.recipeapi_v1.services.RecipeIngredientDAOService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("${openapi.recipeIngredients.base-path:}")
public class RecipeIngredientsApiController implements RecipeIngredientsApi {

    private final RecipeIngredientDAOService service;

    public RecipeIngredientsApiController(RecipeIngredientDAOService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<RecipeDTO> addIngredientsToRecipe(RecipeDTO recipeDTO) {
        return service.addIngredientsToRecipe(recipeDTO);
    }

    @Override
    public ResponseEntity<String> deleteRecipeIngredient(UUID recipeId, UUID ingredientId) {
        return service.removeIngredientFromRecipe(recipeId, ingredientId);
    }
}
