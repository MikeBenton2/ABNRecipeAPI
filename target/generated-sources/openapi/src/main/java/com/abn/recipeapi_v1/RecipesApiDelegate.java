package com.abn.recipeapi_v1;

import com.abn.recipeapi_v1.controllers.RecipesAPIController;
import com.abn.recipeapi_v1.dto.RecipeDTO;
import com.abn.recipeapi_v1.model.GetRecipes200Response;
import com.abn.recipeapi_v1.model.Recipe;
import com.abn.recipeapi_v1.model.UpdatedRecipe;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import jakarta.annotation.Generated;

/**
 * A delegate to be called by the {@link RecipesAPIController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-27T16:17:05.660628+01:00[Europe/Amsterdam]")
public interface RecipesApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /recipes : Create a recipe
     *
     * @param recipe Create Recipe payload (required)
     * @return Recipe created successfully. (status code 201)
     *         or Bad Request. (status code 400)
     *         or Server error (status code 500)
     * @see RecipesApi#createRecipe
     */
    default ResponseEntity<String> createRecipe(RecipeDTO recipe) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 5, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 5, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /recipes/{recipeId} : Delete recipe by id
     *
     * @param recipeId The id of the recipe to retrieve (required)
     * @return Recipe deleted successfully. (status code 200)
     *         or Recipe ID not found (status code 404)
     *         or Unauthorized (status code 401)
     * @see RecipesApi#deleteRecipe
     */
    default ResponseEntity deleteRecipe(Long recipeId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"numberOfServings\" : \"numberOfServings\", \"instructions\" : \"instructions\", \"name\" : \"name\", \"isVegetarian\" : true, \"ingredients\" : [ { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true }, { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true }, { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true }, { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true }, { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true } ], \"id\" : 1 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * GET /recipes/{recipeId} : Info for a specific recipe
     *
     * @param recipeId The id of the recipe to retrieve (required)
     * @return Expected response to a valid request (status code 200)
     * @see RecipesApi#getRecipeById
     */
    default ResponseEntity<RecipeDTO> getRecipeById(Long recipeId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"numberOfServings\" : \"numberOfServings\", \"instructions\" : \"instructions\", \"name\" : \"name\", \"isVegetarian\" : true, \"ingredients\" : [ { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true }, { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true }, { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true }, { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true }, { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true } ], \"id\" : 1 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /recipes : Get all recipes
     *
     * @param page  (required)
     * @param perPage  (required)
     * @param recipeProperties Recipe field properties (optional)
     * @return A paged array of recipes (status code 200)
     *         or Bad Request. (status code 400)
     *         or No recipes found. (status code 404)
     *         or Server error (status code 500)
     * @see RecipesApi#getRecipes
     */
    default ResponseEntity<GetRecipes200Response> getRecipes(Integer page,
        Integer perPage) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"per_page\" : 6, \"has_next\" : true, \"page\" : 0, \"results\" : [ { \"numberOfServings\" : \"numberOfServings\", \"instructions\" : \"instructions\", \"name\" : \"name\", \"isVegetarian\" : true, \"ingredients\" : [ { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true }, { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true }, { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true }, { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true }, { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true } ], \"id\" : 1 }, { \"numberOfServings\" : \"numberOfServings\", \"instructions\" : \"instructions\", \"name\" : \"name\", \"isVegetarian\" : true, \"ingredients\" : [ { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true }, { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true }, { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true }, { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true }, { \"recipes\" : [ null, null ], \"name\" : \"name\", \"id\" : 5, \"included\" : true, \"required\" : true } ], \"id\" : 1 } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 5, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 5, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 5, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PATCH /recipes : Update a recipe
     *
     * @param updatedRecipe Updated Recipe payload (required)
     * @return Recipe updated successfully. (status code 201)
     *         or Bad Request. (status code 400)
     *         or Server error (status code 500)
     * @see RecipesApi#updateRecipe
     */
    default ResponseEntity<String> updateRecipe(RecipeDTO updatedRecipe) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 5, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 5, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
