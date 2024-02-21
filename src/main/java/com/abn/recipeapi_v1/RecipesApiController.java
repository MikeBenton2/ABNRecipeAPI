package com.abn.recipeapi_v1;

import com.abn.recipeapi_v1.model.GetRecipes200Response;
import com.abn.recipeapi_v1.model.Recipe;
import com.abn.recipeapi_v1.model.RecipeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;
import java.net.URI;

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
        GetRecipes200Response response = service.findRecipesFor(page, perPage, fields);
        if (response.getResults().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Recipe> getRecipeById(String recipeId) {
        RecipeProperties properties = new RecipeProperties();
        properties.setId(Integer.valueOf(recipeId));
        List<Recipe> recipes = service.findRecipesBy(properties);

        if (recipes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(recipes.getFirst(), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Void> deleteRecipe(String recipeId) {
        service.deleteRecipeBy(Integer.valueOf(recipeId));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> createRecipe(Recipe recipe) {
        int id = service.addRecipe(recipe);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/" + id)
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
