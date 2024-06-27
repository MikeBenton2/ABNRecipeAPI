package com.abn.recipeapi_v1;

import com.abn.recipeapi_v1.entities.Ingredient;
import com.abn.recipeapi_v1.entities.Recipe;
import com.abn.recipeapi_v1.entities.RecipeIngredient;
import com.abn.recipeapi_v1.filterAndSearch.RecipeSpecification;
import com.abn.recipeapi_v1.model.IngredientDTO;
import com.abn.recipeapi_v1.model.RecipeDTO;
import com.abn.recipeapi_v1.model.SearchRequest;
import com.abn.recipeapi_v1.repositories.RecipeRepository;
import com.abn.recipeapi_v1.services.RecipeDAOService;
import com.abn.recipeapi_v1.services.RecipeIngredientDAOService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipeDAOServiceTests {
	@Mock
	RecipeRepository recipeRepository;
	@Mock
	RecipeIngredientDAOService recipeIngredientDAOService;
	@InjectMocks
	private RecipeDAOService service;
	static List<Ingredient> ingredients = new ArrayList<>();
	static List<Recipe> recipes = new ArrayList<>();
	static List<RecipeDTO> recipeDTOs = new ArrayList<>();

	@BeforeAll
	public static void setupRecipeObjects() {
		Ingredient ingredient = Ingredient.builder()
				.name("ingredient")
				.build();
		ingredients.add(ingredient);
		Recipe recipe = Recipe.builder()
				.name("Test name")
				.instructions("test instructions")
				.isVegetarian(true)
				.numberOfServings(5)
				.build();

		List<RecipeIngredient> recipeIngredients = new ArrayList<>();
		recipeIngredients.add(new RecipeIngredient(recipe, ingredient));
		ingredient.setRecipeIngredients(recipeIngredients);
		recipe.setRecipeIngredients(recipeIngredients);
		recipes.add(recipe);

		Ingredient ingredient2 = Ingredient.builder()
				.name("ingredient2")
				.build();
		ingredients.add(ingredient2);
		Recipe recipe2 = Recipe.builder()
				.name("Test name2")
				.instructions("test instructions2")
				.isVegetarian(false)
				.numberOfServings(4)
				.build();
		ingredient2.setRecipeIngredients(recipeIngredients);
		recipe2.setRecipeIngredients(recipeIngredients);
		recipes.add(recipe2);

		IngredientDTO ingredientDTO = new IngredientDTO();
		ingredientDTO.setName("name");

		RecipeDTO recipeDTO = new RecipeDTO("name", "instructions", true, 5,List.of(ingredientDTO));
		recipeDTO.setId(UUID.randomUUID());
		recipeDTOs.add(recipeDTO);
	}

	@Test
	void getAllRecipes() {
		Page<Recipe> recipePage = new PageImpl<>(recipes);
		when(recipeRepository.findAll(isA(RecipeSpecification.class), isA(PageRequest.class))).thenReturn(recipePage);

		SearchRequest searchRequest = SearchRequest.builder().build();
		ResponseEntity<List<RecipeDTO>> entity = service.findAllRecipes(searchRequest);
		List<RecipeDTO> recipesResponse = Objects.requireNonNull(entity.getBody());

		Assertions.assertEquals(2, recipesResponse.size());
		Assertions.assertEquals(recipesResponse.getFirst().getName(), recipes.getFirst().getName());
		Assertions.assertEquals(recipesResponse.getFirst().getInstructions(), recipes.getFirst().getInstructions());
		Assertions.assertEquals(recipesResponse.getFirst().getIsVegetarian(), recipes.getFirst().getIsVegetarian());
		Assertions.assertEquals(recipesResponse.getFirst().getNumberOfServings(), recipes.getFirst().getNumberOfServings());
		Assertions.assertEquals(recipesResponse.getFirst().getIngredients().getFirst().getName(),
				recipes.getFirst().getRecipeIngredients().getFirst().getIngredient().getName());
	}

	@Test
	void findRecipeByID() {
		when(recipeRepository.findById(isA(UUID.class))).thenReturn(Optional.of(recipes.getFirst()));

		ResponseEntity<RecipeDTO> response = service.getRecipeById(UUID.randomUUID());
		RecipeDTO recipeDTO = response.getBody();

		Assertions.assertNotNull(recipeDTO);
		Assertions.assertEquals(recipeDTO.getName(), recipes.getFirst().getName());
		Assertions.assertEquals(recipeDTO.getInstructions(), recipes.getFirst().getInstructions());
		Assertions.assertEquals(recipeDTO.getIsVegetarian(), recipes.getFirst().getIsVegetarian());
		Assertions.assertEquals(recipeDTO.getNumberOfServings(), recipes.getFirst().getNumberOfServings());
		Assertions.assertEquals(recipeDTO.getIngredients().getFirst().getName(),
				recipes.getFirst().getRecipeIngredients().getFirst().getIngredient().getName());
	}

	@Test
	void createRecipe() {
		when(recipeRepository.save(isA(Recipe.class))).thenReturn(recipes.getFirst());

		ResponseEntity<RecipeDTO> response = service.createRecipe(recipeDTOs.getFirst());

		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	void deleteRecipe() {
		when(recipeRepository.findById(isA(UUID.class))).thenReturn(Optional.of(recipes.getFirst()));

		ResponseEntity<String> response = service.deleteRecipe(UUID.randomUUID());

		Assertions.assertEquals(response.getStatusCode(), HttpStatus.GONE);
	}

	@Test
	void updateRecipe() {
		when(recipeRepository.findById(isA(UUID.class))).thenReturn(Optional.of(recipes.getFirst()));
		when(recipeRepository.save(recipes.getFirst())).thenReturn(recipes.getFirst());

		ResponseEntity<RecipeDTO> response = service.updateRecipe(recipeDTOs.getFirst());

		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
}
