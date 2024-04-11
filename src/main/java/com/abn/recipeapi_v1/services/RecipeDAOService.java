package com.abn.recipeapi_v1.services;

import com.abn.recipeapi_v1.RecipesApiDelegate;
import com.abn.recipeapi_v1.exception.APIRequestException;
import com.abn.recipeapi_v1.exception.ValueDoesNotExistException;
import com.abn.recipeapi_v1.filterAndSearch.RecipeSpecification;
import com.abn.recipeapi_v1.filterAndSearch.SearchRequest;
import com.abn.recipeapi_v1.model.*;
import com.abn.recipeapi_v1.repositories.IngredientRepository;
import com.abn.recipeapi_v1.repositories.RecipeRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.abn.recipeapi_v1.exception.ExceptionConstants.*;

@Service
public class RecipeDAOService implements RecipesApiDelegate {
    private static final Logger logger = LoggerFactory.getLogger(RecipeDAOService.class);
    private List<RecipeDTO> staticRecipes = new ArrayList<>();
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public RecipeDAOService(
            RecipeRepository recipeRepository,
            IngredientRepository ingredientRepository
    ) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        retrieveDefaultRecipesFromJSON();
        saveRecipesToDatabase(staticRecipes);
    }

    public List<RecipeDTO> getStaticRecipes() {
        return staticRecipes;
    }

    //<editor-fold desc="HTTP Methods">
    public ResponseEntity<List<RecipeDTO>> findAllRecipes(SearchRequest searchRequest) {

        List<RecipeDTO> foundRecipes = new ArrayList<>();
        RecipeSpecification recipeSpecification = new RecipeSpecification(searchRequest);
        Pageable pageRequest = RecipeSpecification.getSortingOrder(searchRequest);

        List<Recipe> recipeList = recipeRepository.findAll();

        recipeRepository.findAll(recipeSpecification, pageRequest).forEach(recipe -> {
            RecipeDTO recipeDTO = new RecipeDTO(
                recipe.getName(),
                recipe.getInstructions(),
                recipe.getIsVegetarian(),
                recipe.getNumberOfServings(),
                recipe.getRecipeIngredients().stream()
                        .map(ingredient ->
                                new IngredientDTO(
                                        ingredient.getIngredient().getId(),
                                        ingredient.getIngredient().getName()
                                )
                        ).toList());
            recipeDTO.setId(recipe.getId());
            foundRecipes.add(recipeDTO);
        });

        if (CollectionUtils.isEmpty(foundRecipes)) {
            logger.info(NO_RECIPES_FOUND);
            throw new APIRequestException(NO_RECIPES_FOUND);
        }

        return new ResponseEntity<>(foundRecipes, HttpStatus.OK);
    }

    public ResponseEntity<RecipeDTO> findRecipeById(UUID recipeId) {
        Recipe foundRecipe = recipeRepository.findById(recipeId).orElseThrow(() -> {
            logger.info(RECIPE_NOT_FOUND);
            return new APIRequestException(RECIPE_NOT_FOUND);
        });

        RecipeDTO recipeDTO = new RecipeDTO(
                foundRecipe.getName(),
                foundRecipe.getInstructions(),
                foundRecipe.getIsVegetarian(),
                foundRecipe.getNumberOfServings(),
                foundRecipe.getRecipeIngredients().stream()
                        .map(ingredient ->
                                new IngredientDTO(
                                        ingredient.getIngredient().getId(),
                                        ingredient.getIngredient().getName()
                                )
                        ).toList());
        recipeDTO.setId(foundRecipe.getId());

        return new ResponseEntity<>(recipeDTO, HttpStatus.OK);
    }

    public ResponseEntity<String> createRecipe(RecipeDTO recipeDTO) {
        return saveRecipesToDatabase(List.of(recipeDTO));
    }

    public ResponseEntity<String> deleteRecipe(UUID id) {
        final Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.info(RECIPE_DOES_NOT_EXIST);
                    return new ValueDoesNotExistException(RECIPE_DOES_NOT_EXIST);
                });
        recipeRepository.delete(recipe);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    public ResponseEntity<String> updateRecipe(RecipeDTO updatedRecipe) {
        Recipe recipe = recipeRepository.findById(updatedRecipe.getId())
                .orElseThrow(() -> {
                    logger.info(RECIPE_DOES_NOT_EXIST);
                    return new ValueDoesNotExistException(RECIPE_DOES_NOT_EXIST);
                });

        recipe.setName(updatedRecipe.getName());
        recipe.setInstructions(updatedRecipe.getInstructions());
        recipe.setIsVegetarian(updatedRecipe.getIsVegetarian());
        recipe.setNumberOfServings(updatedRecipe.getNumberOfServings());

        recipeRepository.save(recipe);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    //</editor-fold>

    //<editor-fold desc="Helper Functions">
    private void retrieveDefaultRecipesFromJSON() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("src/main/resources/recipes.json")) {
            //Read JSON file
            Type userListType = new TypeToken<List<RecipeDTO>>(){}.getType();
            staticRecipes = gson.fromJson(reader, userListType);

        } catch (IOException e) {
            logger.info(JSON_PARSE_ERROR + e);
        }
    }

    ResponseEntity<String> saveRecipesToDatabase(List<RecipeDTO> recipes) {
        recipes.forEach(recipeDTO -> {
            Recipe recipe = new Recipe();

            recipe.setName(recipeDTO.getName());
            recipe.setInstructions(recipeDTO.getInstructions());
            recipe.setIsVegetarian(recipeDTO.getIsVegetarian());
            recipe.setNumberOfServings(recipeDTO.getNumberOfServings());
            recipe.setRecipeIngredients(new ArrayList<>());

            Recipe updatedRecipe = recipeRepository.save(recipe);

            recipeDTO.getIngredients()
                    .forEach(recipeIngredient -> {
                        Ingredient ingredient;

                        if (recipeIngredient.getId() != null) {
                            ingredient = ingredientRepository.findById(recipeIngredient.getId())
                                    .orElseThrow(() -> {
                                        logger.info(INGREDIENT_DOES_NOT_EXIST); // should never happen, deeper bug then
                                        return new ValueDoesNotExistException(INGREDIENT_DOES_NOT_EXIST);
                                    });
                        } else {
                            ingredient = ingredientRepository.findIngredientByName(recipeIngredient.getName());
                            if(ingredient == null) {
                                ingredient = ingredientRepository.save(new Ingredient(recipeIngredient.getName()));
                            }
                        }

                        updatedRecipe.addRecipeIngredientsItem(new RecipeIngredient(updatedRecipe, ingredient));
                    });

            recipeRepository.save(updatedRecipe);
        });

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    //</editor-fold>
}

