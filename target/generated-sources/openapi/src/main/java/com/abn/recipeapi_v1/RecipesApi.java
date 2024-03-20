/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.abn.recipeapi_v1;

import com.abn.recipeapi_v1.model.Error;
import com.abn.recipeapi_v1.model.GetRecipes200Response;
import com.abn.recipeapi_v1.model.Recipe;
import com.abn.recipeapi_v1.model.RecipeProperties;
import java.util.UUID;
import com.abn.recipeapi_v1.model.UpdatedRecipe;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-20T12:28:10.528866+01:00[Europe/Amsterdam]")
@Validated
@Tag(name = "recipe", description = "the recipe API")
public interface RecipesApi {

    default RecipesApiDelegate getDelegate() {
        return new RecipesApiDelegate() {};
    }

    /**
     * POST /recipes : Create a recipe
     *
     * @param recipe Create Recipe payload (required)
     * @return Recipe created successfully. (status code 201)
     *         or Bad Request. (status code 400)
     *         or Server error (status code 500)
     */
    @Operation(
        operationId = "createRecipe",
        summary = "Create a recipe",
        tags = { "recipe" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Recipe created successfully.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/recipes",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<String> createRecipe(
        @Parameter(name = "Recipe", description = "Create Recipe payload", required = true) @Valid @RequestBody Recipe recipe
    ) {
        return getDelegate().createRecipe(recipe);
    }


    /**
     * DELETE /recipes/{recipeId} : Delete recipe by id
     *
     * @param recipeId The id of the recipe to retrieve (required)
     * @return Recipe deleted successfully. (status code 200)
     *         or Recipe ID not found (status code 404)
     *         or Unauthorized (status code 401)
     */
    @Operation(
        operationId = "deleteRecipe",
        summary = "Delete recipe by id",
        tags = { "recipe" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Recipe deleted successfully.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "404", description = "Recipe ID not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/recipes/{recipeId}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<String> deleteRecipe(
        @Parameter(name = "recipeId", description = "The id of the recipe to retrieve", required = true, in = ParameterIn.PATH) @PathVariable("recipeId") UUID recipeId
    ) {
        return getDelegate().deleteRecipe(recipeId);
    }


    /**
     * GET /recipes/{recipeId} : Info for a specific recipe
     *
     * @param recipeId The id of the recipe to retrieve (required)
     * @return Expected response to a valid request (status code 200)
     */
    @Operation(
        operationId = "getRecipeById",
        summary = "Info for a specific recipe",
        tags = { "recipe" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Recipe.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/recipes/{recipeId}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<Recipe> getRecipeById(
        @Parameter(name = "recipeId", description = "The id of the recipe to retrieve", required = true, in = ParameterIn.PATH) @PathVariable("recipeId") UUID recipeId
    ) {
        return getDelegate().getRecipeById(recipeId);
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
     */
    @Operation(
        operationId = "getRecipes",
        summary = "Get all recipes",
        tags = { "recipe" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A paged array of recipes", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = GetRecipes200Response.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "404", description = "No recipes found.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/recipes",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<GetRecipes200Response> getRecipes(
        @NotNull @Parameter(name = "page", description = "", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "page", required = true) Integer page,
        @NotNull @Parameter(name = "per_page", description = "", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "per_page", required = true) Integer perPage,
        @Parameter(name = "RecipeProperties", description = "Recipe field properties") @Valid @RequestBody(required = false) RecipeProperties recipeProperties
    ) {
        return getDelegate().getRecipes(page, perPage, recipeProperties);
    }


    /**
     * PATCH /recipes : Update a recipe
     *
     * @param updatedRecipe Updated Recipe payload (required)
     * @return Recipe updated successfully. (status code 201)
     *         or Bad Request. (status code 400)
     *         or Server error (status code 500)
     */
    @Operation(
        operationId = "updateRecipe",
        summary = "Update a recipe",
        tags = { "recipe" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Recipe updated successfully.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/recipes",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<String> updateRecipe(
        @Parameter(name = "UpdatedRecipe", description = "Updated Recipe payload", required = true) @Valid @RequestBody UpdatedRecipe updatedRecipe
    ) {
        return getDelegate().updateRecipe(updatedRecipe);
    }

}
