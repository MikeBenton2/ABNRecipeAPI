package com.abn.recipeapi_v1;

import com.abn.recipeapi_v1.model.*;
import com.fasterxml.uuid.Generators;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RecipeDAOService implements RecipesApiDelegate  {
    private List<Recipe> recipes = new ArrayList<>();
    public RecipeDAOService() {
        retrieveDefaultRecipesFromJSON();
    }

    //<editor-fold desc="HTTP Methods">
    @Override
    public ResponseEntity<GetRecipes200Response> getRecipes(Integer page, Integer perPage, RecipeProperties fields) {
        GetRecipes200Response response = findRecipesFor(page, perPage, fields);
        if (CollectionUtils.isEmpty(response.getResults())) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Recipe> getRecipeById(UUID recipeId) {
        RecipeProperties properties = new RecipeProperties();
        properties.setId(recipeId);
        Set<Recipe> recipes = findRecipesBy(properties);

        if (CollectionUtils.isEmpty(recipes)) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(recipes.stream().findFirst().orElseThrow(), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> createRecipe(Recipe recipe) {
        recipe.setId(Generators.timeBasedGenerator().generate());
        recipes.add(recipe);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{recipeId}")
                .buildAndExpand(recipe.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<String> deleteRecipe(UUID recipeId) {
        boolean success = deleteRecipeBy(recipeId);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<String> updateRecipe(UUID recipeId, UpdatedRecipe recipeToBeUpdated) {
        patchRecipe(recipeId, recipeToBeUpdated);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{recipeID}")
                .buildAndExpand(recipeId)
                .toUri();
        return ResponseEntity.created(location).build();
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
            e.printStackTrace();
        }
    }

    private GetRecipes200Response findRecipesFor(Integer page, Integer perPage, RecipeProperties fields) {

        GetRecipes200Response response = new GetRecipes200Response();
        int lowerRange = page * perPage;
        int upperRange = (lowerRange + perPage) - 1;

        response.setPage(page);
        response.setPerPage(perPage);

        if (!allNull(fields)) {
           response.setResults(findRecipesBy(fields).stream().toList());
        } else {
            try {
                response.setResults(recipes.subList(lowerRange, upperRange));
                response.setHasNext(true);
            } catch (Exception exception) {
                response.setResults(recipes.subList(lowerRange, recipes.size()));
            }
        }

        if (upperRange >= recipes.size()) {
            response.setHasNext(false);
        }

        return response;
    }

    private Set<Recipe> findRecipesBy(@NotNull RecipeProperties fields) {

        List<Predicate<Recipe>> predicates = new ArrayList<>();
        Set<Recipe> matchedRecipes = new HashSet<>();

//        Look into Integration tests via web tab @SpringBootTest

        if (fields.getIngredients() != null) {
            matchedRecipes = findRecipesMatchingIngredients(fields.getIngredients());
        }
        if (fields.getId() != null) {
            predicates.add(recipe -> recipe.getId().equals(fields.getId()));
        }
        if (fields.getName() != null) {
            predicates.add(recipe -> recipe.getName().toLowerCase().contains(fields.getName().toLowerCase()));
        }
        if (fields.getInstructions() != null) {
            predicates.add(recipe -> recipe.getInstructions().toLowerCase().contains(fields.getInstructions().toLowerCase()));
        }
        if (fields.getIsVegetarian() != null) {
            predicates.add(recipe -> recipe.getIsVegetarian().equals(fields.getIsVegetarian()));
        }
        if (fields.getNumberOfServings() != null) {
            predicates.add(recipe -> recipe.getNumberOfServings().equals(fields.getNumberOfServings()));
        }

        if (matchedRecipes.isEmpty()) {
            return recipes.stream()
                    .filter(predicates.stream().reduce(x -> true, Predicate::and))
                    .collect(Collectors.toSet());
        } else {
            return matchedRecipes.stream()
                    .filter(predicates.stream().reduce(x -> true, Predicate::and))
                    .collect(Collectors.toSet());
        }
    }

    private boolean deleteRecipeBy(UUID id) {
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

    private void patchRecipe(UUID id, UpdatedRecipe updatedRecipe) {

        // Find recipe by ID
        RecipeProperties properties = new RecipeProperties();
        properties.setId(id);
        Set<Recipe> recipes = findRecipesBy(properties);
        Recipe recipeToUpdate;

        if (!CollectionUtils.isEmpty(recipes)) {
            recipeToUpdate = recipes.iterator().next();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        updatedRecipe.setId(recipeToUpdate.getId());
        Recipe incompleteRecipe = createRecipeObjectFrom(updatedRecipe);
        Field[] recipeFieldsToUpdate = recipeToUpdate.getClass().getDeclaredFields();

        for (Field recipeField : recipeFieldsToUpdate) {
            recipeField.setAccessible(true);

            Object recipeValueToUpdate;

            try {
                recipeValueToUpdate = recipeField.get(incompleteRecipe);
                if (recipeValueToUpdate != null) {
                    recipeField.set(recipeToUpdate, recipeValueToUpdate); // Should never fail since the value was found
                }
            } catch (Exception ignored) {
                continue;
            }

            recipeField.setAccessible(false);
        }
    }

    /* We want to match the included recipe ingredients count with the field ingredients count. This means, the recipe
    contains all the fields(request) ingredients. Eg. Find me all recipes that include 'Onion' and 'Garlic', but exclude
    'Cheese'. The included count is 2 so if we go through each recipe and it equals 2 at the end then we can add it to
    the list of matching recipes.
    */
    private Set<Recipe> findRecipesMatchingIngredients(List<Ingredient> includedAndExcludedIngredients) {
        int fieldIngredientIncludedCount = 0;
        int recipeIngredientIncludedCount = 0;
        boolean excludeRecipe = false;
        HashSet<Recipe> matchingRecipes = new HashSet<>();

        for (Ingredient includedOrExcludedIngredient : includedAndExcludedIngredients) {
            if (includedOrExcludedIngredient.getIncluded()) {
                fieldIngredientIncludedCount++;
            }
        }

        for (Recipe recipe : recipes) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                for (Ingredient includedOrExcludedIngredient : includedAndExcludedIngredients) {

                    if (includedOrExcludedIngredient.getName().equals(ingredient.getName())) {
                        if (!includedOrExcludedIngredient.getIncluded()) {
                            excludeRecipe = true;
                        } else {
                            recipeIngredientIncludedCount++;
                        }
                    }
                }
            }

            if (excludeRecipe) {
                excludeRecipe = false;
            } else if (fieldIngredientIncludedCount == recipeIngredientIncludedCount) {
                matchingRecipes.add(recipe);
            }

            recipeIngredientIncludedCount = 0;
        }

        return matchingRecipes;
    }

    private Recipe createRecipeObjectFrom(UpdatedRecipe incompleteRecipe) {
        return new Recipe(
                incompleteRecipe.getId(),
                incompleteRecipe.getName(),
                incompleteRecipe.getInstructions(),
                incompleteRecipe.getIsVegetarian(),
                incompleteRecipe.getNumberOfServings(),
                incompleteRecipe.getIngredients()
        );
    }
    //</editor-fold>
}
