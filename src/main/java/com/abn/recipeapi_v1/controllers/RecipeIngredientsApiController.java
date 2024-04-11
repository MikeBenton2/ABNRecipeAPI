package com.abn.recipeapi_v1.controllers;

import com.abn.recipeapi_v1.RecipeIngredientsApi;
import com.abn.recipeapi_v1.RecipeIngredientsApiDelegate;
import com.abn.recipeapi_v1.model.RecipeDTO;
import com.abn.recipeapi_v1.services.RecipeIngredientDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("${openapi.recipeIngredients.base-path:}")
public class RecipeIngredientsApiController implements RecipeIngredientsApi {

    private final RecipeIngredientsApiDelegate delegate;
    private final RecipeIngredientDAOService service;

    public RecipeIngredientsApiController(
            @Autowired(required = false) RecipeIngredientsApiDelegate delegate,
            RecipeIngredientDAOService service
    ) {
        this.delegate = Optional.ofNullable(delegate).orElse(new RecipeIngredientsApiDelegate() {});
        this.service = service;
    }

    @Override
    public RecipeIngredientsApiDelegate getDelegate() {
        return delegate;
    }

    @Override
    public ResponseEntity<String> addIngredientToRecipe(RecipeDTO recipeDTO) {
        return service.addIngredientToRecipe(recipeDTO);
    }

    @Override
    public ResponseEntity<String> deleteRecipeIngredient(UUID recipeId, UUID ingredientId) {
        return service.removeIngredientFromRecipe(recipeId, ingredientId);
    }
}
