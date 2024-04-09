package com.abn.recipeapi_v1;


import com.abn.recipeapi_v1.model.Ingredient;
import com.abn.recipeapi_v1.model.Recipe;

import com.abn.recipeapi_v1.services.RecipeDAOService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

class RecipeDAOServiceTests {

//    RecipeDAOService service = new RecipeDAOService(null);

//    @Test
//    void getAllRecipes() {
//        ResponseEntity<GetRecipes200Response> entity = service.getRecipes(0,10, null);
//        List<Recipe> recipes = Objects.requireNonNull(entity.getBody()).getResults();
//
//        Assertions.assertEquals(4, recipes.size());
//    }
//
//    @Test
//    void getRecipeByID() {
//        ResponseEntity<GetRecipes200Response> entity = service.getRecipes(0,10, null);
//        List<Recipe> recipes = Objects.requireNonNull(entity.getBody()).getResults();
//
//        Recipe recipe = recipes.getFirst();
//
//        ResponseEntity<Recipe> recipeEntity = service.getRecipeById(recipe.getId());
//        Assertions.assertEquals(recipeEntity.getBody(), recipe);
//    }
//
//    @Test
//    void getRecipeByName() {
//        RecipeProperties properties = new RecipeProperties();
//        String expectedRecipeName = "Pizza";
//        properties.setName(expectedRecipeName);
//
//        ResponseEntity<GetRecipes200Response> entity = service.getRecipes(0,10, properties);
//        List<Recipe> recipes = Objects.requireNonNull(entity.getBody()).getResults();
//
//        Assertions.assertEquals(1, recipes.size());
//        Recipe recipe = recipes.getFirst();
//
//        Assertions.assertEquals(expectedRecipeName, recipe.getName());
//    }
//
//    @Test
//    void getRecipeByInstructions() {
//        RecipeProperties properties = new RecipeProperties();
//        String expectedRecipeInstructions = "Lasagna recipe instructions";
//        properties.setInstructions(expectedRecipeInstructions);
//
//        ResponseEntity<GetRecipes200Response> entity = service.getRecipes(0,10, properties);
//        List<Recipe> recipes = Objects.requireNonNull(entity.getBody()).getResults();
//
//        Assertions.assertEquals(1, recipes.size());
//        Recipe recipe = recipes.getFirst();
//
//        Assertions.assertEquals(expectedRecipeInstructions, recipe.getInstructions());
//    }
//
//    @Test
//    void getRecipesByIsVegetarian() {
//        RecipeProperties properties = new RecipeProperties();
//        boolean expectedBoolean = true;
//        properties.setIsVegetarian(expectedBoolean);
//
//        ResponseEntity<GetRecipes200Response> entity = service.getRecipes(0,10, properties);
//        List<Recipe> recipes = Objects.requireNonNull(entity.getBody()).getResults();
//
//        for (Recipe recipe : recipes) {
//            Assertions.assertEquals(expectedBoolean, recipe.getIsVegetarian());
//        }
//    }
//
//    @Test
//    void getRecipesByNumberOfServings() {
//        RecipeProperties properties = new RecipeProperties();
//        String expectedNumberOfServings = "2";
//        properties.setNumberOfServings(expectedNumberOfServings);
//
//        ResponseEntity<GetRecipes200Response> entity = service.getRecipes(0,10, properties);
//        List<Recipe> recipes = Objects.requireNonNull(entity.getBody()).getResults();
//
//        for (Recipe recipe : recipes) {
//            Assertions.assertEquals(expectedNumberOfServings, recipe.getNumberOfServings());
//        }
//    }
//
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

