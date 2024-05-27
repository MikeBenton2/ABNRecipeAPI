package com.abn.recipeapi_v1;


import com.abn.recipeapi_v1.exception.APIRequestException;
import com.abn.recipeapi_v1.model.Filter;
import com.abn.recipeapi_v1.model.SearchRequest;
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
        SearchRequest searchRequest = new SearchRequest();
        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);
        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());

        Assertions.assertEquals(4, recipes.size());
    }

    @Test
    void getAllRecipesWithPageAndNumberOfElements() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setPage(0);
        searchRequest.setNumberOfElements(2);

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(2, recipes.size());
    }

    @Test
    void getAllRecipesWithSortAndOrderByNameAscending() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setSortBy("name");

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(4, recipes.size());
        Assertions.assertEquals("Burgers", recipes.getFirst().getName());
        Assertions.assertEquals("Pizza", recipes.getLast().getName());
    }

    @Test
    void getRecipeByID() {
        SearchRequest searchRequest = new SearchRequest();
        RecipeDTO recipe = Objects.requireNonNull(service.findAllRecipes(searchRequest).getBody()).getFirst();
        ResponseEntity<RecipeDTO> entityRecipe = service.findRecipeById(recipe.getId());
        RecipeDTO foundRecipe = Objects.requireNonNull(entityRecipe.getBody());

        Assertions.assertEquals(recipe, foundRecipe);
    }

    @Test
    void getRecipeByName() {
        String recipeName = "Chickpea Curry";
        Filter filter = new Filter();

        filter.setKey("name");
        filter.setOperation(":");
        filter.setValue(recipeName);
        // key operation value

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setFilters(List.of(filter));

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(1, recipes.size());
        Assertions.assertEquals(recipeName, recipes.getFirst().getName());
    }

    @Test
    void getRecipeByInstructions() {
        String recipeInstructions = "vegetarian recipe instructions";
        Filter filter = new Filter();

        filter.setKey("instructions");
        filter.setOperation(":");
        filter.setValue(recipeInstructions);
        // key operation value

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setFilters(List.of(filter));

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(1, recipes.size());
        Assertions.assertEquals(recipeInstructions, recipes.getFirst().getInstructions());
    }

    @Test
    void getRecipesByIsVegetarian() {
        Filter filter = new Filter();

        filter.setKey("isVegetarian");
        filter.setOperation(":");
        filter.setValue(true);
        // key operation value

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setFilters(List.of(filter));

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(2, recipes.size());

        for (RecipeDTO recipeDTO : recipes) {
            Assertions.assertEquals(true, recipeDTO.getIsVegetarian());
        }
    }

    @Test
    void getRecipesByNumberOfServings() {
        Filter filter = new Filter();

        filter.setKey("numberOfServings");
        filter.setOperation(":");
        filter.setValue(2);
        // key operation value

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setFilters(List.of(filter));

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(1, recipes.size());
    }

    @Test
    void getRecipesWithMultipleFilters_isVegetarian_numberOfServings() {
        Filter filter1 = new Filter();
        Filter filter2 = new Filter();

        filter1.setKey("isVegetarian");
        filter1.setOperation(":");
        filter1.setValue(false);

        filter2.setKey("numberOfServings");
        filter2.setOperation(":");
        filter2.setValue(2);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setFilters(List.of(filter1, filter2));

        assertThrows(APIRequestException.class, () -> service.findAllRecipes(searchRequest));
    }

    @Test
    void getRecipesByIncludedIngredients() {
        Filter filter1 = new Filter();
        Filter filter2 = new Filter();

        filter1.setKey("recipeIngredients");
        filter1.setOperation(":");
        filter1.setValue("Garlic");

        filter2.setKey("recipeIngredients");
        filter2.setOperation(":");
        filter2.setValue("Beef");

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setFilters(List.of(filter1, filter2));

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(1, recipes.size());
    }

    @Test
    void getRecipesByExcludedIngredients() {
        Filter filter = new Filter();

        filter.setKey("recipeIngredients");
        filter.setOperation("!:");
        filter.setValue("Beef");
        // key operation value

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setFilters(List.of(filter));

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(3, recipes.size());
    }

    @Test
    void getRecipesByNumberOfServingsAndIncludedIngredients() {
        Filter filter1 = new Filter();
        Filter filter2 = new Filter();

        filter1.setKey("numberOfServings");
        filter1.setOperation(":");
        filter1.setValue(2);

        filter2.setKey("recipeIngredients");
        filter2.setOperation(":");
        filter2.setValue("Garlic");

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setFilters(List.of(filter1, filter2));

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(1, recipes.size());
    }

    @Test
    void getRecipesByInstructionsAndExcludedIngredients() {
        Filter filter1 = new Filter();
        Filter filter2 = new Filter();

        filter1.setKey("instructions");
        filter1.setOperation(":");
        filter1.setValue("instructions");

        filter2.setKey("recipeIngredients");
        filter2.setOperation("!:");
        filter2.setValue("Pizza Sauce");

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setFilters(List.of(filter1, filter2));

        ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);

        List<RecipeDTO> recipes = Objects.requireNonNull(entity.getBody());
        Assertions.assertEquals(3, recipes.size());
    }
}

