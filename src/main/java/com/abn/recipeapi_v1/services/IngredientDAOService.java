package com.abn.recipeapi_v1.services;

import com.abn.recipeapi_v1.dto.IngredientDTO;
import com.abn.recipeapi_v1.exception.ValueAlreadyExistsException;
import com.abn.recipeapi_v1.exception.ValueDoesNotExistException;
import com.abn.recipeapi_v1.model.Ingredient;
import com.abn.recipeapi_v1.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

import static com.abn.recipeapi_v1.exception.ExceptionConstants.*;

@Service
public class IngredientDAOService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientDAOService(
            IngredientRepository ingredientRepository
    ) {
        this.ingredientRepository = ingredientRepository;
    }

    public ResponseEntity<IngredientDTO> getIngredientByID(Long id) {
        IngredientDTO ingredientDTO = ingredientRepository.findById(id).map(IngredientDTO::new).orElseThrow(
                //            log.error(DOES_NOT_EXIST_INGREDIENT);
                () -> new ValueDoesNotExistException(INGREDIENT_DOES_NOT_EXIST)
        );

        return new ResponseEntity<>(ingredientDTO, HttpStatus.OK);
    }

    public ResponseEntity<List<IngredientDTO>>  getAllIngredients() {
       List <IngredientDTO> ingredients = ingredientRepository.findAll().stream().map(IngredientDTO::new).toList();
        if (CollectionUtils.isEmpty(ingredients)) {
            //            log.error(NO_RECIPES_FOUND);
            throw new ValueDoesNotExistException(NO_RECIPES_FOUND);
        }

        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    public ResponseEntity<String> createIngredient(String name) {
        if (ingredientRepository.existsByNameIgnoreCase(name)) {
//            log.error(ALREADY_EXISTS_INGREDIENT);
            throw new ValueAlreadyExistsException(INGREDIENT_ALREADY_EXISTS);
        }
        Ingredient ingredient = ingredientRepository.save(new Ingredient(name));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<String> update(IngredientDTO ingredientDTO) {
        Ingredient ingredient = ingredientRepository.findById(ingredientDTO.getId())
                .orElseThrow(
                        //            log.error(DOES_NOT_EXIST_INGREDIENT);
                        () -> new ValueDoesNotExistException(INGREDIENT_DOES_NOT_EXIST)
                );

        ingredient.setName(ingredientDTO.getName());
        ingredient = ingredientRepository.save(ingredient);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<String> deleteById(Long id) {
        final Optional<Ingredient> ingredient = ingredientRepository.findById(id);

        if(ingredient.isPresent()) {
            ingredientRepository.delete(ingredient.get());
            return new ResponseEntity<>(HttpStatus.GONE);
        } else {
            throw new ValueDoesNotExistException(INGREDIENT_DOES_NOT_EXIST);
        }
    }
}
