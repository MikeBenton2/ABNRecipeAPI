package com.abn.recipeapi_v1;


import com.abn.recipeapi_v1.exception.APIRequestException;
import com.abn.recipeapi_v1.filterAndSearch.Filter;
import com.abn.recipeapi_v1.filterAndSearch.SearchRequest;
import com.abn.recipeapi_v1.model.RecipeDTO;
import com.abn.recipeapi_v1.services.RecipeDAOService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecipeApiV1Application.class)
@Transactional
class RecipeDAOServiceTests {

    @Autowired
    private RecipeDAOService service;

    @Test
    void getAllRecipesNoSearchRequest() {
        SearchRequest searchRequest = new SearchRequest(null, null, null, null);
        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);
        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());

        Assertions.assertEquals(4, recipes.size());
    }

    @Test
    void getAllRecipesWithPageAndNumberOfElements() {
        SearchRequest searchRequest = new SearchRequest(null, 0, 2, null);

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(2, recipes.size());
    }

    @Test
    void getAllRecipesWithSortAndOrderByNameAscending() {
        SearchRequest searchRequest = new SearchRequest(null, null, null, "name");

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(4, recipes.size());
        Assertions.assertEquals("Burgers", recipes.getFirst().getName());
        Assertions.assertEquals("Pizza", recipes.getLast().getName());
    }

    @Test
    void getRecipeByID() {
        SearchRequest searchRequest = new SearchRequest(null, null, null, null);
        RecipeDTO recipe = Objects.requireNonNull(service.findAllRecipes(searchRequest).getBody()).getFirst();
        ResponseEntity<RecipeDTO> entityRecipe = service.findRecipeById(recipe.getId());
        RecipeDTO foundRecipe = Objects.requireNonNull(entityRecipe.getBody());

        Assertions.assertEquals(recipe, foundRecipe);
    }

    @Test
    void getRecipeByName() {
        List<Filter> filters = new ArrayList<>();
        String recipeName = "Chickpea Curry";
        filters.add(new Filter("name", ":", recipeName));

        SearchRequest searchRequest = new SearchRequest(filters, null, null, null);

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(1, recipes.size());
        Assertions.assertEquals(recipeName, recipes.getFirst().getName());
    }

    @Test
    void getRecipeByInstructions() {
        List<Filter> filters = new ArrayList<>();
        String recipeInstructions = "vegetarian recipe instructions";
        filters.add(new Filter("instructions", ":", recipeInstructions));

        SearchRequest searchRequest = new SearchRequest(filters, null, null, null);

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(1, recipes.size());
        Assertions.assertEquals(recipeInstructions, recipes.getFirst().getInstructions());
    }

    @Test
    void getRecipesByIsVegetarian() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("isVegetarian", ":", true));

        SearchRequest searchRequest = new SearchRequest(filters, null, null, null);

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(2, recipes.size());

        for (RecipeDTO recipeDTO : recipes) {
            Assertions.assertEquals(true, recipeDTO.getIsVegetarian());
        }
    }

    @Test
    void getRecipesByNumberOfServings() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("numberOfServings", ":", 2));

        SearchRequest searchRequest = new SearchRequest(filters, null, null, null);

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(1, recipes.size());
    }

    @Test
    void getRecipesWithMultipleFilters_isVegetarian_numberOfServings() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("isVegetarian", ":", false));
        filters.add(new Filter("numberOfServings", ":", 2));

        SearchRequest searchRequest = new SearchRequest(filters, null, null, null);

        assertThrows(APIRequestException.class, () -> service.findAllRecipes(searchRequest));
    }

    @Test
    void getRecipesByIncludedIngredients() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("recipeIngredients", ":", "Garlic"));
        filters.add(new Filter("recipeIngredients", ":", "Beef"));

        SearchRequest searchRequest = new SearchRequest(filters, null, null, null);

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(1, recipes.size());
    }

    @Test
    void getRecipesByExcludedIngredients() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("recipeIngredients", "!:", "Beef"));

        SearchRequest searchRequest = new SearchRequest(filters, null, null, null);

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(3, recipes.size());
    }

    @Test
    void getRecipesByNumberOfServingsAndIncludedIngredients() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("numberOfServings", ":", 2));
        filters.add(new Filter("recipeIngredients", ":", "Garlic"));

        SearchRequest searchRequest = new SearchRequest(filters, null, null, null);

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(1, recipes.size());
    }

    @Test
    void getRecipesByInstructionsAndExcludedIngredients() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("instructions", ":", "instructions"));
        filters.add(new Filter("recipeIngredients", "!:", "Pizza Sauce"));

        SearchRequest searchRequest = new SearchRequest(filters, null, null, null);

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(3, recipes.size());
    }
}

