package com.abn.recipeapi_v1.controllers;

import com.abn.recipeapi_v1.IngredientsApi;
import com.abn.recipeapi_v1.model.IngredientDTO;
import com.abn.recipeapi_v1.services.IngredientDAOService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("${openapi.ingredient.base-path:}")
public class IngredientsApiController implements IngredientsApi {

    private final IngredientDAOService service;
    public IngredientsApiController(IngredientDAOService service) {
        this.service = service;
    }

	@Override
    public ResponseEntity<List<IngredientDTO>> getIngredients() {
        return service.getAllIngredients();
    }

    @Override
    public ResponseEntity<IngredientDTO> getIngredientById(UUID id) {
        return service.getIngredientByID(id);
    }

    @Override
    public ResponseEntity<IngredientDTO> createIngredient(IngredientDTO ingredientDTO) {
        return service.createIngredient(ingredientDTO.getName());
    }

    @Override
    public ResponseEntity<IngredientDTO> updateIngredient(IngredientDTO ingredientDTO) {
        return service.update(ingredientDTO);
    }

    @Override
    public ResponseEntity<String> deleteIngredient(UUID id) {
        return service.deleteById(id);
    }
}
