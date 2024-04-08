package com.abn.recipeapi_v1.controllers;

import com.abn.recipeapi_v1.dto.IngredientDTO;
import com.abn.recipeapi_v1.dto.RecipeDTO;
import com.abn.recipeapi_v1.model.Ingredient;
import com.abn.recipeapi_v1.model.Recipe;
import com.abn.recipeapi_v1.services.IngredientDAOService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IngredientsAPIController {

    private final IngredientDAOService service;

    public IngredientsAPIController(IngredientDAOService ingredientService) {
        this.service = ingredientService;
    }

    @GetMapping("/ingredients/{id}")
    public ResponseEntity<IngredientDTO> findById(@PathVariable long id) {
        return service.getIngredientByID(id);
    }

    @GetMapping("/ingredients")
    public ResponseEntity<List<IngredientDTO>> findAllIngredients() {
        return service.getAllIngredients();
    }

    @PostMapping("/ingredients")
    public ResponseEntity<String> createIngredient(@RequestBody IngredientDTO ingredientDTO) {
        return service.createIngredient(ingredientDTO.getName());
    }

    @PutMapping("/ingredients")
    public ResponseEntity<String> updateIngredient(@RequestBody IngredientDTO ingredientDTO) {
        return service.update(ingredientDTO);
    }

    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable long id) {
        return service.deleteById(id);
    }
}
