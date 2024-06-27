package com.abn.recipeapi_v1.services;

import com.abn.recipeapi_v1.entities.Ingredient;
import com.abn.recipeapi_v1.entities.Recipe;
import com.abn.recipeapi_v1.entities.RecipeIngredient;
import com.abn.recipeapi_v1.exception.APIRequestException;
import com.abn.recipeapi_v1.exception.ValueDoesNotExistException;
import com.abn.recipeapi_v1.mapping.ObjectMapping;
import com.abn.recipeapi_v1.model.IngredientDTO;
import com.abn.recipeapi_v1.model.RecipeDTO;
import com.abn.recipeapi_v1.repositories.RecipeIngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

import static com.abn.recipeapi_v1.exception.ExceptionConstants.RECIPE_INGREDIENT_ALREADY_EXISTS;
import static com.abn.recipeapi_v1.exception.ExceptionConstants.RECIPE_INGREDIENT_DOES_NOT_EXIST;

@Service
public class RecipeIngredientDAOService {
    private static final Logger logger = LoggerFactory.getLogger(IngredientDAOService.class);
    private final RecipeIngredientRepository recipeIngredientRepository;
	private final IngredientDAOService ingredientDAOService;

    @Autowired
    public RecipeIngredientDAOService(
            RecipeIngredientRepository recipeIngredientRepository,
			IngredientDAOService ingredientDAOService
    ) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientDAOService = ingredientDAOService;
    }

    public ResponseEntity<RecipeDTO> addIngredientsToRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = ObjectMapping.mapRecipeDTOtoRecipe(recipeDTO);

        recipeDTO.getIngredients().forEach(ingredientDTO -> {
			saveRecipeIngredient(ingredientDTO, recipe);
		});

		RecipeDTO updatedRecipe = ObjectMapping.mapRecipeToRecipeDTO(recipe);
        return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
    }

    public ResponseEntity<String> removeIngredientFromRecipe(UUID recipeId, UUID ingredientId) {
        final RecipeIngredient recipeIngredient = recipeIngredientRepository.findByRecipeIdAndIngredientId(recipeId, ingredientId)
                .orElseThrow(() -> {
                    logger.info(RECIPE_INGREDIENT_DOES_NOT_EXIST);
                    return new ValueDoesNotExistException(RECIPE_INGREDIENT_DOES_NOT_EXIST);
                });

        recipeIngredientRepository.delete(recipeIngredient);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

	public void saveRecipeIngredient(IngredientDTO ingredientDTO, Recipe recipe) {
		Ingredient ingredient;

		ingredient = ingredientDAOService.findIngredientByName(ingredientDTO.getName());

		if (ingredient == null) {
			ingredient = ingredientDAOService.saveIngredientToRepository(ingredientDTO.getName());
		}

		if(!recipeIngredientRepository.existsByRecipeIdAndIngredientId(recipe.getId(), ingredient.getId())) {
			recipeIngredientRepository.save(new RecipeIngredient(recipe, ingredient));
		} else {
			logger.info(RECIPE_INGREDIENT_ALREADY_EXISTS);
		}
	}

}
