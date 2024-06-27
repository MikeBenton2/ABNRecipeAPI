package com.abn.recipeapi_v1.services;

import com.abn.recipeapi_v1.RecipesApiDelegate;
import com.abn.recipeapi_v1.entities.Recipe;
import com.abn.recipeapi_v1.exception.APIRequestException;
import com.abn.recipeapi_v1.exception.ValueDoesNotExistException;
import com.abn.recipeapi_v1.filterAndSearch.RecipeSpecification;
import com.abn.recipeapi_v1.mapping.ObjectMapping;
import com.abn.recipeapi_v1.model.RecipeDTO;
import com.abn.recipeapi_v1.model.SearchRequest;
import com.abn.recipeapi_v1.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.abn.recipeapi_v1.exception.ExceptionConstants.NO_RECIPES_FOUND;
import static com.abn.recipeapi_v1.exception.ExceptionConstants.RECIPE_DOES_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class RecipeDAOService implements RecipesApiDelegate {
    private static final Logger logger = LoggerFactory.getLogger(RecipeDAOService.class);
    private final RecipeRepository recipeRepository;
	private final RecipeIngredientDAOService recipeIngredientDAOService;

    //<editor-fold desc="HTTP Methods">
    public ResponseEntity<List<RecipeDTO>> findAllRecipes(SearchRequest searchRequest) {

        List<RecipeDTO> foundRecipes = new ArrayList<>();
        RecipeSpecification recipeSpecification = new RecipeSpecification(searchRequest);
        Pageable pageRequest = RecipeSpecification.getSortingOrder(searchRequest);

        recipeRepository.findAll(recipeSpecification, pageRequest).forEach(recipe -> {
			RecipeDTO recipeDTO = ObjectMapping.mapRecipeToRecipeDTO(recipe);
            foundRecipes.add(recipeDTO);
        });

        if (CollectionUtils.isEmpty(foundRecipes)) {
            logger.info(NO_RECIPES_FOUND);
            throw new APIRequestException(NO_RECIPES_FOUND);
        }

        return new ResponseEntity<>(foundRecipes, HttpStatus.OK);
    }

    public ResponseEntity<RecipeDTO> getRecipeById(UUID recipeId) {
        Recipe recipe = findRecipeByID(recipeId);

        RecipeDTO recipeDTO = ObjectMapping.mapRecipeToRecipeDTO(recipe);

        return new ResponseEntity<>(recipeDTO, HttpStatus.OK);
    }

    public ResponseEntity<RecipeDTO> createRecipe(RecipeDTO recipeDTO) {
        return saveRecipeToDatabase(recipeDTO);
    }

    public ResponseEntity<String> deleteRecipe(UUID id) {
        final Recipe recipe = findRecipeByID(id);

        recipeRepository.delete(recipe);

        return ResponseEntity.status(HttpStatus.GONE).build();
    }

	public ResponseEntity<RecipeDTO> updateRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = findRecipeByID(recipeDTO.getId());

        recipe.setName(recipeDTO.getName());
        recipe.setInstructions(recipeDTO.getInstructions());
        recipe.setIsVegetarian(recipeDTO.getIsVegetarian());
        recipe.setNumberOfServings(recipeDTO.getNumberOfServings());

        recipe = recipeRepository.save(recipe);
		RecipeDTO updatedRecipe = ObjectMapping.mapRecipeToRecipeDTO(recipe);

		return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
    }
    //</editor-fold>

    //<editor-fold desc="Helper Functions">
    private ResponseEntity<RecipeDTO> saveRecipeToDatabase(RecipeDTO recipeDTO) {
		Recipe recipe = ObjectMapping.mapRecipeDTOtoRecipe(recipeDTO);

        Recipe updatedRecipe = recipeRepository.save(recipe);

        recipeDTO.getIngredients().forEach(ingredientDTO -> {
			recipeIngredientDAOService.saveRecipeIngredient(ingredientDTO, updatedRecipe);
		});

        recipeRepository.save(updatedRecipe);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

	public Recipe findRecipeByID(UUID id) {
		return recipeRepository.findById(id)
				.orElseThrow(() -> {
					logger.info(RECIPE_DOES_NOT_EXIST);
					return new ValueDoesNotExistException(RECIPE_DOES_NOT_EXIST);
				});
	}

	public void deleteAllRecipes() {
		recipeRepository.deleteAll();
	}
    //</editor-fold>
}

