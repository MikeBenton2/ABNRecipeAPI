package com.abn.recipeapi_v1;

import com.abn.recipeapi_v1.model.Error;
import com.abn.recipeapi_v1.model.RecipeDTO;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

/**
 * A delegate to be called by the {@link RecipeIngredientsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-24T16:27:39.935937+02:00[Europe/Amsterdam]")
public interface RecipeIngredientsApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /recipeIngredients : Add a ingredient to a recipe
     *
     * @param recipeDTO Add Ingredient to Recipe (required)
     * @return Ingredient added successfully. (status code 201)
     *         or Bad Request. (status code 400)
     *         or Server error (status code 500)
     * @see RecipeIngredientsApi#addIngredientToRecipe
     */
    default ResponseEntity<String> addIngredientToRecipe(RecipeDTO recipeDTO) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 6, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 6, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /recipeIngredients/{recipeId}/ingredients/{ingredientId} : Delete ingredient from a recipe
     *
     * @param recipeId The id of the recipe (required)
     * @param ingredientId The id of the ingredient (required)
     * @return Ingredient deleted successfully. (status code 200)
     *         or Ingredient or recipe not found (status code 404)
     *         or Unauthorized (status code 401)
     * @see RecipeIngredientsApi#deleteRecipeIngredient
     */
    default ResponseEntity<String> deleteRecipeIngredient(UUID recipeId,
        UUID ingredientId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
