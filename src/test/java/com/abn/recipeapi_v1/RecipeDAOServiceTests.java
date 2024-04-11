package com.abn.recipeapi_v1;


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
    void getRecipesByMultipleFilters() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("isVegetarian", ":", false));
        filters.add(new Filter("numberOfServings", ":", 2));

        SearchRequest searchRequest = new SearchRequest(filters, null, null, null);

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(1, recipes.size());
    }

//    @Test
//    void getRecipesByIncludedIngredients() {
//        RecipeProperties properties = new RecipeProperties();
//        List<Ingredient> expectedIngredients = List.of(
//                new Ingredient("Cheese", true, true),
//                new Ingredient("Peppers", true, true)
//        );
//
//
//        properties.setIngredients(expectedIngredients);
//
//        ResponseEntity<GetRecipes200Response> entity = service.getRecipes(0,10, properties);
//        List<Recipe> recipes = Objects.requireNonNull(entity.getBody()).getResults();
//
//        int count = recipeIngredientCount(recipes, expectedIngredients);
//
//        Assertions.assertEquals(count, expectedIngredients.size());
//    }
//
//    @Test
//    void getRecipesByExcludedIngredients() {
//        RecipeProperties properties = new RecipeProperties();
//        List<Ingredient> expectedIngredients = List.of(
//                new Ingredient("Cheese", true, false),
//                new Ingredient("Peppers", true, false)
//        );
//
//        properties.setIngredients(expectedIngredients);
//
//        ResponseEntity<GetRecipes200Response> entity = service.getRecipes(0,10, properties);
//        List<Recipe> recipes = Objects.requireNonNull(entity.getBody()).getResults();
//
//        Assertions.assertEquals(1, recipes.size());
//    }
//
//    @Test
//    void getRecipesByIncludedAndExcludedIngredients() {
//        RecipeProperties properties = new RecipeProperties();
//        List<Ingredient> expectedIngredients = List.of(
//                new Ingredient("Cheese", true, true),
//                new Ingredient("Peppers", true, false)
//        );
//        properties.setIngredients(expectedIngredients);
//
//        ResponseEntity<GetRecipes200Response> entity = service.getRecipes(0,10, properties);
//        List<Recipe> recipes = Objects.requireNonNull(entity.getBody()).getResults();
//
//        int count = recipeIngredientCount(recipes, expectedIngredients);
//
//        Assertions.assertEquals(count, expectedIngredients.size());
//    }
//
//    @Test
//    void getRecipesByMultipleIncludedAndExcludedIngredients() {
//        RecipeProperties properties = new RecipeProperties();
//        List<Ingredient> expectedIngredients = List.of(
//                new Ingredient("Cheese", true, true),
//                new Ingredient("Onion", true, true),
//                new Ingredient("Beef", true, false),
//                new Ingredient("Pasta Sauce", true, false)
//        );
//        properties.setIngredients(expectedIngredients);
//
//        ResponseEntity<GetRecipes200Response> entity = service.getRecipes(0,10, properties);
//        List<Recipe> recipes = Objects.requireNonNull(entity.getBody()).getResults();
//
//        int count = recipeIngredientCount(recipes, expectedIngredients);
//
//        Assertions.assertEquals(count, expectedIngredients.size());
//    }
//
//    @Test
//    void getRecipesByNumberOfServingsAndIncludedIngredients() {
//        RecipeProperties properties = new RecipeProperties();
//        List<Ingredient> expectedIngredients = List.of(
//                new Ingredient("Cheese", true, true)
//        );
//        String expectedNumberOfServings = "6";
//
//        properties.setIngredients(expectedIngredients);
//        properties.setNumberOfServings(expectedNumberOfServings);
//
//        ResponseEntity<GetRecipes200Response> entity = service.getRecipes(0,10, properties);
//        List<Recipe> recipes = Objects.requireNonNull(entity.getBody()).getResults();
//
//        int count = recipeIngredientCount(recipes, expectedIngredients);
//
//        Assertions.assertEquals(count, expectedIngredients.size());
//
//        for (Recipe recipe : recipes) {
//            Assertions.assertEquals(recipe.getNumberOfServings(), expectedNumberOfServings);
//        }
//    }
//
//    @Test
//    void getRecipesByInstructionsAndExcludedIngredients() {
//        RecipeProperties properties = new RecipeProperties();
//        List<Ingredient> expectedIngredients = List.of(
//                new Ingredient("Cheese", true, false)
//        );
//        String expectedInstructions = "vegetarian recipe instructions";
//
//        properties.setIngredients(expectedIngredients);
//        properties.setInstructions(expectedInstructions);
//
//        ResponseEntity<GetRecipes200Response> entity = service.getRecipes(0,10, properties);
//        List<Recipe> recipes = Objects.requireNonNull(entity.getBody()).getResults();
//
//        int count = recipeIngredientCount(recipes, expectedIngredients);
//
//        Assertions.assertEquals(count, 0);
//
//        for (Recipe recipe : recipes) {
//            Assertions.assertEquals(recipe.getInstructions(), expectedInstructions);
//        }
//    }
//
//    private int recipeIngredientCount(List<Recipe> recipes, List<Ingredient> expectedIngredients) {
//        int includedCount = 0;
//
//        for (Recipe recipe : recipes) {
//            for (Ingredient ingredient : expectedIngredients) {
//                for (Ingredient recipeIngredient : recipe.getIngredients()) {
//                    if (ingredient.getName().equals(recipeIngredient.getName()) &&
//                            ingredient.getIncluded() == recipeIngredient.getIncluded()) {
//                        includedCount++;
//                    }
//                }
//            }
//        }
//
//        return includedCount;
//    }
}

