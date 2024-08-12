package com.abn.recipeapi_v1.controllers;

import com.abn.recipeapi_v1.RecipesApi;
import com.abn.recipeapi_v1.model.RecipeDTO;
import com.abn.recipeapi_v1.model.SearchRequest;
import com.abn.recipeapi_v1.services.RecipeDAOService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("${openapi.recipe.base-path:}")
public class RecipesApiController implements RecipesApi {

    private final RecipeDAOService service;
    public RecipesApiController(RecipeDAOService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<RecipeDTO>> getRecipes(SearchRequest searchRequest) {
        return service.findAllRecipes(searchRequest);
    }

    @Override
    public ResponseEntity<RecipeDTO> getRecipeById(UUID recipeId) {
        return service.getRecipeById(recipeId);
    }

    @Override
    public ResponseEntity<RecipeDTO> createRecipe(RecipeDTO recipeDTO) {
        return service.createRecipe(recipeDTO);
    }

    @Override
    public ResponseEntity<RecipeDTO> updateRecipe(RecipeDTO recipeDTO) {
        return service.updateRecipe(recipeDTO);
    }

    @Override
    public ResponseEntity<String> deleteRecipe(UUID recipeId) {
        return service.deleteRecipe(recipeId);
    }
}
