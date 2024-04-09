package com.abn.recipeapi_v1.services;

import com.abn.recipeapi_v1.exception.ValueDoesNotExistException;
import com.abn.recipeapi_v1.model.Ingredient;
import com.abn.recipeapi_v1.model.Recipe;
import com.abn.recipeapi_v1.model.RecipeDTO;
import com.abn.recipeapi_v1.model.RecipeIngredient;
import com.abn.recipeapi_v1.repositories.IngredientRepository;
import com.abn.recipeapi_v1.repositories.RecipeIngredientRepository;
import com.abn.recipeapi_v1.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.abn.recipeapi_v1.exception.ExceptionConstants.*;

@Service
public class RecipeIngredientDAOService {

    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public RecipeIngredientDAOService(
            RecipeIngredientRepository recipeIngredientRepository,
            RecipeRepository recipeRepository,
            IngredientRepository ingredientRepository
    ) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public ResponseEntity<String> addIngredientToRecipe(RecipeDTO recipeDTO) {

        if (recipeDTO.getId() == null) {
            throw new ValueDoesNotExistException(NO_RECIPE_ID_PROVIDED);
        }

        Recipe recipe = recipeRepository.findById(recipeDTO.getId()).orElseThrow(
                () -> new ValueDoesNotExistException(RECIPE_DOES_NOT_EXIST));

        recipeDTO.getIngredients().forEach(
                ingredientDTO -> {
                    Ingredient ingredient;

                    if (ingredientDTO.getId() != null) {
                        ingredient = ingredientRepository.findById(ingredientDTO.getId()).orElseThrow(
                                // log, should never happen though
                                () -> new ValueDoesNotExistException(INGREDIENT_DOES_NOT_EXIST));
                    } else {
                        ingredient = ingredientRepository.findIngredientByName(ingredientDTO.getName());

                        if (ingredient == null) {
                            ingredient = ingredientRepository.save(new Ingredient(ingredientDTO.getName()));
                        }
                    }

                    if(!recipeIngredientRepository.existsByRecipeIdAndIngredientId(recipe.getId(), ingredient.getId())) {
                        recipeIngredientRepository.save(new RecipeIngredient(recipe, ingredient));
                    }
                });

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<String> removeIngredientFromRecipe(UUID recipeId, UUID ingredientId) {
        final RecipeIngredient recipeIngredient = recipeIngredientRepository.findByRecipeIdAndIngredientId(recipeId, ingredientId)
                .orElseThrow(
                    () -> new ValueDoesNotExistException(RECIPE_INGREDIENT_DOES_NOT_EXIST));

        recipeIngredientRepository.delete(recipeIngredient);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

}
