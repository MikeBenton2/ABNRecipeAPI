package com.abn.recipeapi_v1;

import com.abn.recipeapi_v1.model.GetRecipes200Response;
import com.abn.recipeapi_v1.model.Ingredient;
import com.abn.recipeapi_v1.model.Recipe;
import com.abn.recipeapi_v1.model.RecipeProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

// Once we have the database up and running, we can set the default recipes with ID's of 0, 1 and 2
// Then, the min will be 3 and max will be equal to (min + interval). once the amount of used ID's becomes equal to the
// max, then we set min = max and again set max to (min + interval)

@Component
public class RecipeDAOService {
    private final List<Recipe> recipes = new ArrayList<>();
    private final RecipeIDGenerator recipeIDGenerator = new RecipeIDGenerator();
    public RecipeDAOService() {
        generateDefaultRecipes();
    }
    private void generateDefaultRecipes() {
        Recipe recipe1 = new Recipe(
                "vegetarian recipe name",
                "vegetarian recipe instructions",
                true,
                "5",
                new ArrayList<>(Arrays.asList(new Ingredient("name", true),
                        new Ingredient("second name", true)
                )
                ));
        recipe1.setId(recipeIDGenerator.generateID());
        recipes.add(recipe1);

        Recipe recipe2 = new Recipe(
                "vegetarian recipe name",
                "vegetarian recipe instructions",
                true,
                "5",
                new ArrayList<>(Arrays.asList(new Ingredient("name", true),
                        new Ingredient("second name", true)
                )
                ));
        recipe2.setId(recipeIDGenerator.generateID());
        recipes.add(recipe2);

        Recipe recipe3 = new Recipe(
                "recipe name",
                "recipe instructions",
                true,
                "3",
                new ArrayList<>(Arrays.asList(new Ingredient("name", true),
                        new Ingredient("second name", true)
                )
                ));
        recipe3.setId(recipeIDGenerator.generateID());
        recipes.add(recipe3);

        Recipe recipe4 = new Recipe(
                "recipe name",
                "recipe instructions",
                true,
                "3",
                new ArrayList<>(Arrays.asList(new Ingredient("name", true),
                        new Ingredient("second name", true)
                )
                ));
        recipe4.setId(recipeIDGenerator.generateID());
        recipes.add(recipe4);

        Recipe recipe5 = new Recipe(
                "recipe name",
                "recipe instructions",
                true,
                "3",
                new ArrayList<>(Arrays.asList(new Ingredient("name", true),
                        new Ingredient("second name", true)
                )
                ));
        recipe5.setId(recipeIDGenerator.generateID());
        recipes.add(recipe5);

        Recipe recipe6 = new Recipe(
                "vegetarian recipe name",
                "vegetarian recipe instructions",
                true,
                "5",
                new ArrayList<>(Arrays.asList(new Ingredient("name", true),
                        new Ingredient("second name", true)
                )
                ));
        recipe6.setId(recipeIDGenerator.generateID());
        recipes.add(recipe6);

        Recipe recipe7 = new Recipe(
                "vegetarian recipe name",
                "vegetarian recipe instructions",
                true,
                "5",
                new ArrayList<>(Arrays.asList(new Ingredient("name", true),
                        new Ingredient("second name", true)
                )
                ));
        recipe7.setId(recipeIDGenerator.generateID());
        recipes.add(recipe7);

        Recipe recipe8 = new Recipe(
                "recipe name",
                "recipe instructions",
                true,
                "3",
                new ArrayList<>(Arrays.asList(new Ingredient("name", true),
                        new Ingredient("second name", true)
                )
                ));
        recipe8.setId(recipeIDGenerator.generateID());
        recipes.add(recipe8);

        Recipe recipe9 = new Recipe(
                "recipe name",
                "recipe instructions",
                true,
                "3",
                new ArrayList<>(Arrays.asList(new Ingredient("name", true),
                        new Ingredient("second name", true)
                )
                ));
        recipe9.setId(recipeIDGenerator.generateID());
        recipes.add(recipe9);

        Recipe recipe10 = new Recipe(
                "recipe name",
                "recipe instructions",
                true,
                "3",
                new ArrayList<>(Arrays.asList(new Ingredient("name", true),
                        new Ingredient("second name", true)
                )
                ));
        recipe10.setId(recipeIDGenerator.generateID());
        recipes.add(recipe10);

        Recipe recipe11 = new Recipe(
                "recipe name",
                "recipe instructions",
                true,
                "3",
                new ArrayList<>(Arrays.asList(new Ingredient("name", true),
                        new Ingredient("second name", true)
                )
                ));
        recipe11.setId(recipeIDGenerator.generateID());
        recipes.add(recipe11);
    }

    public GetRecipes200Response findRecipesFor(Integer page, Integer perPage, RecipeProperties fields) {

        GetRecipes200Response response = new GetRecipes200Response();

        if (!allNull(fields)) {
           response.setResults(findRecipesBy(fields));
           return response;
        }

        response.setPage(page);
        response.setPerPage(perPage);
        
        int lowerRange = (page - 1) * perPage;
        int upperRange = (page * perPage) - 1;

        if (upperRange >= recipes.size()) {
            response.setHasNext(false);
        }

        try {
            response.setResults(recipes.subList(lowerRange, upperRange));
            response.setHasNext(true);
            return response;
        } catch (Exception exception) {
            response.setResults(recipes.subList(lowerRange, recipes.size()));
            return response;
        }
    }

    public List<Recipe> findRecipesBy(RecipeProperties fields) {

        List<Predicate<Recipe>> allPredicates = new ArrayList<>();

        if (fields.getId() != null && fields.getId() >= 0) {
            allPredicates.add(recipe -> recipe.getId().equals(fields.getId()));
        }
        if (fields.getName() != null) {
            allPredicates.add(recipe -> recipe.getName().contains(fields.getName()));
        }
        if (fields.getInstructions() != null) {
            allPredicates.add(recipe -> recipe.getInstructions().contains(fields.getInstructions()));
        }
        if (fields.getIsVegetarian() != null) {
            allPredicates.add(recipe -> recipe.getIsVegetarian().equals(fields.getIsVegetarian()));
        }
        if (fields.getNumberOfServings() != null) {
            allPredicates.add(recipe -> recipe.getNumberOfServings().equals(fields.getNumberOfServings()));
        }

        if (allPredicates.isEmpty()) {
            return null;
        }

        try {
            return recipes.stream()
                    .filter(allPredicates.stream().reduce(x -> true, Predicate::and)).toList();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public int addRecipe(Recipe recipe) {
        int id = recipeIDGenerator.generateID();
        recipe.setId(id);
        recipes.add(recipe);
        return id;
    }

    public void deleteRecipeBy(int id) {
        Predicate<? super Recipe> predicate = recipe -> recipe.getId().equals(id);
        boolean found = recipes.removeIf(predicate);

        if (!found) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private static boolean allNull(Object target) {
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
}
