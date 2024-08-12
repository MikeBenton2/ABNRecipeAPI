package com.abn.recipeapi_v1.mapping;

import com.abn.recipeapi_v1.entities.Ingredient;
import com.abn.recipeapi_v1.entities.Recipe;
import com.abn.recipeapi_v1.model.IngredientDTO;
import com.abn.recipeapi_v1.model.RecipeDTO;

import java.util.ArrayList;

public class ObjectMapping {
	public static RecipeDTO mapRecipeToRecipeDTO(Recipe recipe) {

		RecipeDTO recipeDTO = new RecipeDTO(
				recipe.getName(),
				recipe.getInstructions(),
				recipe.getIsVegetarian(),
				recipe.getNumberOfServings(),
				recipe.getRecipeIngredients().stream().map(recipeIngredient -> {
					IngredientDTO ingredientDTO = new IngredientDTO(
							recipeIngredient.getIngredient().getName()
					);
					ingredientDTO.setId(recipeIngredient.getIngredient().getId());
					return ingredientDTO;
				}).toList()
		);
		recipeDTO.setId(recipe.getId());

		return recipeDTO;
	}

	public static Recipe mapRecipeDTOtoRecipe(RecipeDTO recipeDTO) {
		return new Recipe(
				recipeDTO.getId(),
				recipeDTO.getName(),
				recipeDTO.getInstructions(),
				recipeDTO.getIsVegetarian(),
				recipeDTO.getNumberOfServings(),
				new ArrayList<>()
		);
	}

	public static IngredientDTO mapIngredientToIngredientDTO(Ingredient ingredient) {
		IngredientDTO ingredientDTO = new IngredientDTO(ingredient.getName());
		ingredientDTO.setId(ingredient.getId());
		return ingredientDTO;
	}
}
