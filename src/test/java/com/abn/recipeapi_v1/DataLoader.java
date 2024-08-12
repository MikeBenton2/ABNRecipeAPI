package com.abn.recipeapi_v1;

import com.abn.recipeapi_v1.model.RecipeDTO;
import com.abn.recipeapi_v1.services.RecipeDAOService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@TestConfiguration
@RequiredArgsConstructor // this is the better way instead of AutoWired
public class DataLoader {

	private final RecipeDAOService recipeDAOService;

	private final ObjectMapper objectMapper;

	@PostConstruct // this automatically calls once it's constructed during spring initialization
	public void loadData() {
		recipeDAOService.deleteAllRecipes();

		InputStream inputStream = DataLoader.class.getClassLoader().getResourceAsStream("recipes.json");
		try {
			List<RecipeDTO> recipes = Arrays.asList(objectMapper.readValue(inputStream, RecipeDTO[].class));
			recipes.forEach(recipeDAOService::createRecipe);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
