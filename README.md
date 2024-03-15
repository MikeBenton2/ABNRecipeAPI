# ABNRecipeAPI
API which allows users to manage their favourite recipes.


This API allows users to manage their favourite recipes. It allows adding, updating, removing and fetching recipes. Additionally users can filter available recipes based on one or more of the following criteria:
1. Whether or not the dish is vegetarian
2. The number of servings
3. Specific ingredients (either include or exclude)
4. Text search within the instructions.

For example, the API should be able to handle the following search requests:
• All vegetarian recipes
• Recipes that can serve 4 persons and have “potatoes” as an ingredient
• Recipes without “salmon” as an ingredient that has “oven” in the instructions



TO DO:
In order to run this API locally, please run the command "mvn clean install" in order to generate all the files generated from OpenAPI generator.
