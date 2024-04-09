package com.abn.recipeapi_v1.controllers;

import com.abn.recipeapi_v1.RecipesApi;
import com.abn.recipeapi_v1.RecipesApiDelegate;
import com.abn.recipeapi_v1.model.*;
import com.abn.recipeapi_v1.services.RecipeDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("${openapi.recipe.base-path:}")
public class RecipesAPIController implements RecipesApi {

    private final RecipesApiDelegate delegate;
    private final RecipeDAOService service;
    public RecipesAPIController(
            @Autowired(required = false) RecipesApiDelegate delegate,
            RecipeDAOService service
    ) {
        this.delegate = Optional.ofNullable(delegate).orElse(new RecipesApiDelegate() {});
        this.service = service;
    }

    @Override
    public RecipesApiDelegate getDelegate() {
        return delegate;
    }

    @Override
    public ResponseEntity<List<RecipeDTO>> getRecipes() {
        return service.findAllRecipes();
    }

    @Override
    public ResponseEntity<RecipeDTO> getRecipeById(UUID recipeId) {
        return service.findRecipeById(recipeId);
    }

    @Override
    public ResponseEntity<String> createRecipe(RecipeDTO recipeDTO) {
        return service.createRecipe(recipeDTO);
    }

    @Override
    public ResponseEntity<String> updateRecipe(RecipeDTO recipeDTO) {
        return service.updateRecipe(recipeDTO);
    }

    @Override
    public ResponseEntity<String> deleteRecipe(UUID recipeId) {
        return service.deleteRecipe(recipeId);
    }
}
