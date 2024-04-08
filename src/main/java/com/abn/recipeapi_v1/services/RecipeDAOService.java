package com.abn.recipeapi_v1.services;

import com.abn.recipeapi_v1.dto.IngredientDTO;
import com.abn.recipeapi_v1.dto.RecipeDTO;
import com.abn.recipeapi_v1.exception.ValueDoesNotExistException;
import com.abn.recipeapi_v1.repositories.IngredientRepository;
import com.abn.recipeapi_v1.repositories.RecipeIngredientRepository;
import com.abn.recipeapi_v1.repositories.RecipeRepository;
import com.abn.recipeapi_v1.RecipesApiDelegate;
import com.abn.recipeapi_v1.exception.APIRequestException;
import com.abn.recipeapi_v1.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Predicate;

import static com.abn.recipeapi_v1.exception.ExceptionConstants.*;

@Service
public class RecipeDAOService implements RecipesApiDelegate {
    private List<Recipe> recipes = new ArrayList<>();
    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;

    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    public RecipeDAOService(
            RecipeRepository recipeRepository,
            IngredientRepository ingredientRepository,
            RecipeIngredientRepository recipeIngredientRepository
    ) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        retrieveDefaultRecipesFromJSON();
        saveRecipesToDatabase();
    }

    //<editor-fold desc="HTTP Methods">
    @Override
    public ResponseEntity<GetRecipes200Response> getRecipes(Integer page, Integer perPage) {
        GetRecipes200Response response = new GetRecipes200Response();

        final List<RecipeDTO> recipes = recipeRepository.findAll().stream().
                map(recipe -> new RecipeDTO(
                        recipe,
                        recipe.getRecipeIngredients().stream().map(IngredientDTO::new).toList())).toList();
        response.setResults(recipes);

        if (CollectionUtils.isEmpty(response.getResults())) {
            // log
            throw new APIRequestException(NO_RECIPES_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RecipeDTO> getRecipeById(Long recipeId) {
        RecipeDTO recipeDTO = recipeRepository.findById(recipeId).
                map(recipe -> new RecipeDTO(
                        recipe,
                        recipe.getRecipeIngredients().stream().map(IngredientDTO::new).toList())).orElseThrow(() ->
                                // log
                                new APIRequestException(RECIPE_NOT_FOUND)
        );

        return new ResponseEntity<>(recipeDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();

        recipe.setName(recipeDTO.getName());
        recipe.setInstructions(recipeDTO.getInstructions());
        recipe.setIsVegetarian(recipeDTO.getVegetarian());
        recipe.setNumberOfServings(recipeDTO.getNumberOfServings());
        recipe.setRecipeIngredients(new ArrayList<>());

        recipeDTO.getIngredients()
                .forEach(recipeIngredient -> {
                    Ingredient ingredient;

                    if (recipeIngredient.getId() != null) {
                        ingredient = ingredientRepository.findById(recipeIngredient.getId())
                                .orElseThrow(() -> {
//                                    log.error(NOT_FOUND_INGREDIENT); should never happen, deeper bug then
                                    return new ValueDoesNotExistException(INGREDIENT_DOES_NOT_EXIST);
                                });
                    } else {
                        if (ingredientRepository.existsByNameIgnoreCase(recipeIngredient.getName())) {
                            ingredient = ingredientRepository.findIngredientByName(recipeIngredient.getName());
                        } else {
                            ingredient = ingredientRepository.save(new Ingredient(recipeIngredient.getName()));
                        }
                    }

                    recipe.addIngredientsItem(new RecipeIngredient(recipe, ingredient));
                });

        recipeRepository.save(recipe);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<String> deleteRecipe(Long id) {
        final Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ValueDoesNotExistException(RECIPE_DOES_NOT_EXIST));
        recipeRepository.delete(recipe);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    @Override
    public ResponseEntity<String> updateRecipe(RecipeDTO updatedRecipe) {
        Recipe recipe = recipeRepository.findById(updatedRecipe.getId())
                .orElseThrow(() -> {
//                    log.error(NOT_FOUND_RECIPE);
                    return new ValueDoesNotExistException(RECIPE_DOES_NOT_EXIST);
                });

        recipe.setName(updatedRecipe.getName());
        recipe.setInstructions(updatedRecipe.getInstructions());
        recipe.setIsVegetarian(updatedRecipe.getVegetarian());
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
            Type userListType = new TypeToken<List<Recipe>>(){}.getType();
            recipes = gson.fromJson(reader, userListType);

        } catch (IOException e) {
            // log error here
            e.printStackTrace();
        }
    }

    void saveRecipesToDatabase() {
        recipes.forEach(recipe -> {
            Recipe newRecipe = new Recipe();

            newRecipe.setName(recipe.getName());
            newRecipe.setInstructions(recipe.getInstructions());
            newRecipe.setIsVegetarian(recipe.getIsVegetarian());
            newRecipe.setNumberOfServings(recipe.getNumberOfServings());

            Recipe updatedRecipe = recipeRepository.save(newRecipe);

            recipe.getRecipeIngredients().forEach(recipeIngredient -> {
                Ingredient ingredient;
                Long id = recipeIngredient.getIngredient().getId();
                String ingredientName = recipeIngredient.getIngredient().getName();

                if(id != null) {
                    ingredient = ingredientRepository.findById(id).orElseThrow(() -> {
                        // log error. Should never happen. Data corruption if so
                        return new NoSuchElementException(INGREDIENT_DOES_NOT_EXIST);
                    });
                } else {
                    ingredient = ingredientRepository.findIngredientByName(ingredientName);

                    if(ingredient == null) {
                        Ingredient newIngredient = new Ingredient(recipeIngredient.getIngredient().getName());
                        ingredient = ingredientRepository.save(newIngredient);
                    }
                }

                updatedRecipe.getRecipeIngredients().add(new RecipeIngredient(updatedRecipe, ingredient));
            });
            recipeRepository.save(updatedRecipe);
        });
    }

//    private GetRecipes200Response findRecipesFor(Integer page, Integer perPage, RecipeProperties fields) {
//
//        GetRecipes200Response response = new GetRecipes200Response();
//        int lowerRange = page * perPage;
//        int upperRange = (lowerRange + perPage) - 1;
//
//        response.setPage(page);
//        response.setPerPage(perPage);
//
//        if (!allNull(fields)) {
//           response.setResults(findRecipesBy(fields).stream().toList());
//        } else {
//            try {
//                response.setResults(recipes.subList(lowerRange, upperRange));
//                response.setHasNext(true);
//            } catch (Exception exception) {
//                response.setResults(recipes.subList(lowerRange, recipes.size()));
//            }
//        }
//
//        if (upperRange >= recipes.size()) {
//            response.setHasNext(false);
//        }
//
//        return response;
//    }
//
//    private Set<Recipe> findRecipesBy(@NotNull RecipeProperties fields) {
//
//        List<Predicate<Recipe>> predicates = new ArrayList<>();
//        Set<Recipe> matchedRecipes = new HashSet<>();
//
////        Look into Integration tests via web tab @SpringBootTest
//
////        if (fields.getIngredients() != null) {
////            matchedRecipes = findRecipesMatchingIngredients(fields.getIngredients());
////        }
//        if (fields.getId() != null) {
//            predicates.add(recipe -> recipe.getId().equals(fields.getId()));
//        }
//        if (fields.getName() != null) {
//            predicates.add(recipe -> recipe.getName().toLowerCase().contains(fields.getName().toLowerCase()));
//        }
//        if (fields.getInstructions() != null) {
//            predicates.add(recipe -> recipe.getInstructions().toLowerCase().contains(fields.getInstructions().toLowerCase()));
//        }
//        if (fields.getIsVegetarian() != null) {
//            predicates.add(recipe -> recipe.getIsVegetarian().equals(fields.getIsVegetarian()));
//        }
//        if (fields.getNumberOfServings() != null) {
//            predicates.add(recipe -> recipe.getNumberOfServings().equals(fields.getNumberOfServings()));
//        }
//
//        if (matchedRecipes.isEmpty()) {
//            return recipes.stream()
//                    .filter(predicates.stream().reduce(x -> true, Predicate::and))
//                    .collect(Collectors.toSet());
//        } else {
//            return matchedRecipes.stream()
//                    .filter(predicates.stream().reduce(x -> true, Predicate::and))
//                    .collect(Collectors.toSet());
//        }
//    }

    private boolean deleteRecipeBy(Long id) {
        Predicate<? super Recipe> predicate = recipe -> recipe.getId().equals(id);
        return recipes.removeIf(predicate);
    }

    private static boolean allNull(Object target) {
        if (target == null) { return true; }

        return Arrays.stream(target.getClass()
                        .getDeclaredFields())
                .peek(f -> f.setAccessible(true))
                .map(f -> getFieldValue(f, target))
                .allMatch(Objects::isNull);
    }

    private static Object getFieldValue(Field field, Object target) {
        try {
            return field.get(target);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

//    private void patchRecipe(UpdatedRecipe updatedRecipe) {
//
//        // Find recipe by ID
//        RecipeProperties properties = new RecipeProperties();
//        properties.setId(updatedRecipe.getId());
//        Set<Recipe> recipes = findRecipesBy(properties);
//        Recipe recipeToUpdate;
//
//        if (!CollectionUtils.isEmpty(recipes)) {
//            recipeToUpdate = recipes.iterator().next();
//        } else {
//            throw new APIRequestException("Recipe with ID: " + updatedRecipe.getId() + "Not Found");
//        }
//
//        updatedRecipe.setId(recipeToUpdate.getId());
//        Recipe incompleteRecipe = createRecipeObjectFrom(updatedRecipe);
//        Field[] recipeFieldsToUpdate = recipeToUpdate.getClass().getDeclaredFields();
//
//        for (Field recipeField : recipeFieldsToUpdate) {
//            recipeField.setAccessible(true);
//
//            Object recipeValueToUpdate;
//
//            try {
//                recipeValueToUpdate = recipeField.get(incompleteRecipe);
//                if (recipeValueToUpdate != null) {
//                    recipeField.set(recipeToUpdate, recipeValueToUpdate); // Should never fail since the value was found
//                }
//            } catch (Exception ignored) {
//                continue;
//            }
//
//            recipeField.setAccessible(false);
//        }
//    }

    /* We want to match the included recipe ingredients count with the field ingredients count. This means, the recipe
    contains all the fields(request) ingredients. Eg. Find me all recipes that include 'Onion' and 'Garlic', but exclude
    'Cheese'. The included count is 2 so if we go through each recipe and it equals 2 at the end then we can add it to
    the list of matching recipes.
    */
//    private Set<Recipe> findRecipesMatchingIngredients(List<Ingredient> includedAndExcludedIngredients) {
//        int fieldIngredientIncludedCount = 0;
//        int recipeIngredientIncludedCount = 0;
//        boolean excludeRecipe = false;
//        HashSet<Recipe> matchingRecipes = new HashSet<>();
//
//        for (Ingredient includedOrExcludedIngredient : includedAndExcludedIngredients) {
//            if (includedOrExcludedIngredient.getIncluded()) {
//                fieldIngredientIncludedCount++;
//            }
//        }
//
//        for (Recipe recipe : recipes) {
//            for (Ingredient ingredient : recipe.getIngredients()) {
//                for (Ingredient includedOrExcludedIngredient : includedAndExcludedIngredients) {
//
//                    if (includedOrExcludedIngredient.getName().equals(ingredient.getName())) {
//                        if (!includedOrExcludedIngredient.getIncluded()) {
//                            excludeRecipe = true;
//                        } else {
//                            recipeIngredientIncludedCount++;
//                        }
//                    }
//                }
//            }
//
//            if (excludeRecipe) {
//                excludeRecipe = false;
//            } else if (fieldIngredientIncludedCount == recipeIngredientIncludedCount) {
//                matchingRecipes.add(recipe);
//            }
//
//            recipeIngredientIncludedCount = 0;
//        }
//
//        return matchingRecipes;
//    }

    private Recipe createRecipeObjectFrom(UpdatedRecipe incompleteRecipe) {
//        return new Recipe(
//                incompleteRecipe.getName(),
//                incompleteRecipe.getInstructions(),
//                incompleteRecipe.getIsVegetarian(),
//                incompleteRecipe.getNumberOfServings(),
//                incompleteRecipe.getIngredients()
//        );
        return new Recipe();
    }
    //</editor-fold>
}

