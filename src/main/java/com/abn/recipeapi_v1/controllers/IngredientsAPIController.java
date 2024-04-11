package com.abn.recipeapi_v1.controllers;

import com.abn.recipeapi_v1.IngredientsApi;
import com.abn.recipeapi_v1.IngredientsApiDelegate;
import com.abn.recipeapi_v1.model.IngredientDTO;
import com.abn.recipeapi_v1.services.IngredientDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("${openapi.ingredient.base-path:}")
public class IngredientsAPIController implements IngredientsApi {

    private final IngredientsApiDelegate delegate;
    private final IngredientDAOService service;
    public IngredientsAPIController(
            @Autowired(required = false) IngredientsApiDelegate delegate,
            IngredientDAOService service
    ) {
        this.delegate = Optional.ofNullable(delegate).orElse(new IngredientsApiDelegate() {});
        this.service = service;
    }

    public IngredientsApiDelegate getDelegate() {
        return delegate;
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
    public ResponseEntity<String> createIngredient(IngredientDTO ingredientDTO) {
        return service.createIngredient(ingredientDTO.getName());
    }

    @Override
    public ResponseEntity<String> updateIngredient(IngredientDTO ingredientDTO) {
        return service.update(ingredientDTO);
    }

    @Override
    public ResponseEntity<String> deleteIngredient(UUID id) {
        return service.deleteById(id);
    }
}
