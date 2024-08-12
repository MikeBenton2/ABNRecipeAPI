package com.abn.recipeapi_v1.services;

import com.abn.recipeapi_v1.entities.Ingredient;
import com.abn.recipeapi_v1.exception.ValueAlreadyExistsException;
import com.abn.recipeapi_v1.exception.ValueDoesNotExistException;
import com.abn.recipeapi_v1.mapping.ObjectMapping;
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
import java.util.UUID;

import static com.abn.recipeapi_v1.exception.ExceptionConstants.*;

@Service
public class IngredientDAOService {
    private static final Logger logger = LoggerFactory.getLogger(IngredientDAOService.class);
    private final IngredientRepository ingredientRepository;
    @Autowired
    public IngredientDAOService(
            IngredientRepository ingredientRepository
    ) {
        this.ingredientRepository = ingredientRepository;
    }

    public ResponseEntity<List<IngredientDTO>>  getAllIngredients() {
       List <IngredientDTO> ingredientDTOs = ingredientRepository.findAll().stream().map(ObjectMapping::mapIngredientToIngredientDTO).toList();

        if (CollectionUtils.isEmpty(ingredientDTOs)) {
            logger.info(NO_INGREDIENTS_FOUND);
            throw new ValueDoesNotExistException(NO_INGREDIENTS_FOUND);
        }

        return new ResponseEntity<>(ingredientDTOs, HttpStatus.OK);
    }

    public ResponseEntity<IngredientDTO> getIngredientByID(UUID id) {
		Ingredient ingredient = findIngredientByID(id);
		IngredientDTO ingredientDTO = ObjectMapping.mapIngredientToIngredientDTO(ingredient);

        return new ResponseEntity<>(ingredientDTO, HttpStatus.OK);
    }

    public ResponseEntity<IngredientDTO> createIngredient(String name) {
        Ingredient ingredient = saveIngredientToRepository(name);
		IngredientDTO ingredientDTO = ObjectMapping.mapIngredientToIngredientDTO(ingredient);

        return new ResponseEntity<>(ingredientDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<IngredientDTO> update(IngredientDTO ingredientDTO) {
		Ingredient ingredient = findIngredientByID(ingredientDTO.getId());

        ingredient.setName(ingredientDTO.getName());
		ingredient = ingredientRepository.save(ingredient);
		IngredientDTO updatedIngredient = ObjectMapping.mapIngredientToIngredientDTO(ingredient);

        return new ResponseEntity<>(updatedIngredient, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteById(UUID id) {
		Ingredient ingredient = findIngredientByID(id);

		ingredientRepository.delete(ingredient);
		return new ResponseEntity<>(HttpStatus.GONE);
    }

	public Ingredient saveIngredientToRepository(String name) {
		if (ingredientRepository.existsByNameIgnoreCase(name)) {
			logger.info(INGREDIENT_ALREADY_EXISTS);
			throw new ValueAlreadyExistsException(INGREDIENT_ALREADY_EXISTS);
		}
		return ingredientRepository.save(new Ingredient(name));
	}

	public Ingredient findIngredientByID(UUID id) {
		return ingredientRepository.findById(id)
				.orElseThrow(() -> {
					logger.info(INGREDIENT_DOES_NOT_EXIST); // should never happen, deeper bug then
					return new ValueDoesNotExistException(INGREDIENT_DOES_NOT_EXIST);
				});
	}

	public Ingredient findIngredientByName(String name) {
		return ingredientRepository.findIngredientByName(name);
	}
}
