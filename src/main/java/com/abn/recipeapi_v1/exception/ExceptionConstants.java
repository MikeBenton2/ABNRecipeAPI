package com.abn.recipeapi_v1.exception;

public class ExceptionConstants {

    private ExceptionConstants() {}

    public static String JSON_PARSE_ERROR = "Unable to parse JSON file due to exception: ";
    public static String INGREDIENT_ALREADY_EXISTS = "Ingredient already exists";
    public static String INGREDIENT_DOES_NOT_EXIST = "Ingredient does not exist";
    public static String NO_INGREDIENTS_FOUND = "No ingredients found";
    public static String RECIPE_DOES_NOT_EXIST = "Recipe does not exist.";
    public static String RECIPE_NOT_FOUND = "Recipe not found.";
    public static String NO_RECIPE_ID_PROVIDED = "Please provide the Recipe ID when adding ingredients";
    public static String NO_RECIPES_FOUND = "No recipes found.";
    public static String RECIPE_INGREDIENT_DOES_NOT_EXIST = "RecipeIngredient does not exist";
}
