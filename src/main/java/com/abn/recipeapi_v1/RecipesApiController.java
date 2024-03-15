package com.abn.recipeapi_v1;

import com.abn.recipeapi_v1.model.GetRecipes200Response;
import com.abn.recipeapi_v1.model.Recipe;
import com.abn.recipeapi_v1.model.RecipeProperties;
import com.abn.recipeapi_v1.model.UpdatedRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Generated;
import java.util.Optional;
import java.util.UUID;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-16T14:42:28.751731+01:00[Europe/Amsterdam]")
@Controller
@RequestMapping("${openapi.recipe.base-path:}")
public class RecipesApiController implements RecipesApi {

    private final RecipesApiDelegate delegate;
    private final RecipeDAOService service;

    public RecipesApiController(
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
    public ResponseEntity<GetRecipes200Response> getRecipes(Integer page, Integer perPage, RecipeProperties fields) {
        return service.getRecipes(page, perPage, fields);
    }

    @Override
    public ResponseEntity<Recipe> getRecipeById(UUID recipeId) {
        return service.getRecipeById(recipeId);
    }

    @Override
    public ResponseEntity<String> deleteRecipe(UUID recipeId) {
        return service.deleteRecipe(recipeId);
    }

    @Override
    public ResponseEntity<String> createRecipe(Recipe recipe) {
        return service.createRecipe(recipe);
    }

    @Override
    public ResponseEntity<String> updateRecipe(UUID recipeId, UpdatedRecipe recipeToBeUpdated) {
        return service.updateRecipe(recipeId, recipeToBeUpdated);
    }
}
