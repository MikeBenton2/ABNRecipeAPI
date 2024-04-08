package com.abn.recipeapi_v1.controllers;

import com.abn.recipeapi_v1.RecipesApi;
import com.abn.recipeapi_v1.RecipesApiDelegate;
import com.abn.recipeapi_v1.dto.RecipeDTO;
import com.abn.recipeapi_v1.model.*;
import com.abn.recipeapi_v1.services.RecipeDAOService;
import com.abn.recipeapi_v1.services.RecipeIngredientDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Generated;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-16T14:42:28.751731+01:00[Europe/Amsterdam]")
@Controller
@RequestMapping("${openapi.recipe.base-path:}")
public class RecipesAPIController implements RecipesApi {

    private final RecipesApiDelegate delegate;
    private final RecipeDAOService service;
    private final RecipeIngredientDAOService recipeIngredientDAOService;

    public RecipesAPIController(
            @Autowired(required = false) RecipesApiDelegate delegate,
            RecipeDAOService service,
            RecipeIngredientDAOService recipeIngredientDAOService
    ) {
        this.delegate = Optional.ofNullable(delegate).orElse(new RecipesApiDelegate() {});
        this.service = service;
        this.recipeIngredientDAOService = recipeIngredientDAOService;
    }

    @Override
    public RecipesApiDelegate getDelegate() {
        return delegate;
    }

    @Override
    public ResponseEntity<GetRecipes200Response> getRecipes(Integer page, Integer perPage) {
        return service.getRecipes(page, perPage);
    }

    @Override
    public ResponseEntity<RecipeDTO> getRecipeById(Long recipeId) {
        return service.getRecipeById(recipeId);
    }

    @Override
    public ResponseEntity<String> deleteRecipe(Long recipeId) {
        return service.deleteRecipe(recipeId);
    }

    @Override
    public ResponseEntity<String> createRecipe(RecipeDTO recipe) {
        return service.createRecipe(recipe);
    }

    @Override
    public ResponseEntity<String> updateRecipe(RecipeDTO updatedRecipe) {
        return service.updateRecipe(updatedRecipe);
    }

    @DeleteMapping("/recipes/{recipeId}/ingredients/{ingredientId}")
    public ResponseEntity<String> removeRecipeIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId) {
        return recipeIngredientDAOService.removeIngredientFromRecipe(recipeId, ingredientId);
    }

    @PostMapping("/recipeIngredients")
    ResponseEntity<String> addIngredientToRecipe(@RequestBody RecipeDTO recipeDTO) {
        return recipeIngredientDAOService.addIngredientToRecipe(recipeDTO);
    }
}
