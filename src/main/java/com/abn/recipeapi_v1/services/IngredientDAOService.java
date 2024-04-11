package com.abn.recipeapi_v1.services;

import com.abn.recipeapi_v1.exception.ValueAlreadyExistsException;
import com.abn.recipeapi_v1.exception.ValueDoesNotExistException;
import com.abn.recipeapi_v1.model.Ingredient;
import com.abn.recipeapi_v1.model.IngredientDTO;
import com.abn.recipeapi_v1.repositories.IngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.abn.recipeapi_v1.exception.ExceptionConstants.*;

@Service
public class IngredientDAOService {
    private final IngredientRepository ingredientRepository;
    static Logger logger = LoggerFactory.getLogger(IngredientDAOService.class);
    @Autowired
    public IngredientDAOService(
            IngredientRepository ingredientRepository
    ) {
        this.ingredientRepository = ingredientRepository;
    }

    public ResponseEntity<List<IngredientDTO>>  getAllIngredients() {
       List <IngredientDTO> ingredients = ingredientRepository.findAll().stream().map(ingredient ->
           new IngredientDTO(
                   ingredient.getId(),
                   ingredient.getName()
           )).toList();

        if (CollectionUtils.isEmpty(ingredients)) {
            logger.info(NO_INGREDIENTS_FOUND);
            throw new ValueDoesNotExistException(NO_INGREDIENTS_FOUND);
        }

        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    public ResponseEntity<IngredientDTO> getIngredientByID(UUID id) {
        IngredientDTO ingredientDTO = ingredientRepository.findById(id).map(ingredient ->
                new IngredientDTO(
                        ingredient.getId(),
                        ingredient.getName()
                )).orElseThrow(() -> {
            logger.info(INGREDIENT_DOES_NOT_EXIST);
            return new ValueDoesNotExistException(INGREDIENT_DOES_NOT_EXIST);
        });

        return new ResponseEntity<>(ingredientDTO, HttpStatus.OK);
    }

    public ResponseEntity<String> createIngredient(String name) {
        if (ingredientRepository.existsByNameIgnoreCase(name)) {
            logger.info(INGREDIENT_ALREADY_EXISTS);
            throw new ValueAlreadyExistsException(INGREDIENT_ALREADY_EXISTS);
        }
        ingredientRepository.save(new Ingredient(name));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<String> update(IngredientDTO ingredientDTO) {
        Ingredient ingredient = ingredientRepository.findById(ingredientDTO.getId())
                .orElseThrow(() -> {
                    logger.info(INGREDIENT_DOES_NOT_EXIST);
                    return new ValueDoesNotExistException(INGREDIENT_DOES_NOT_EXIST);
                });

        ingredient.setName(ingredientDTO.getName());
        ingredientRepository.save(ingredient);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<String> deleteById(UUID id) {
        final Optional<Ingredient> ingredient = ingredientRepository.findById(id);

        if(ingredient.isPresent()) {
            ingredientRepository.delete(ingredient.get());
            return new ResponseEntity<>(HttpStatus.GONE);
        } else {
            logger.info(INGREDIENT_DOES_NOT_EXIST);
            throw new ValueDoesNotExistException(INGREDIENT_DOES_NOT_EXIST);
        }
    }
}
