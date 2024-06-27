package com.abn.recipeapi_v1;

import com.abn.recipeapi_v1.model.Filter;
import com.abn.recipeapi_v1.model.RecipeDTO;
import com.abn.recipeapi_v1.model.SearchRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(DataLoader.class)

@SpringBootTest
@AutoConfigureMockMvc
class RecipeDAOServiceIntegrationTests {

    @Autowired
    private MockMvc mockMvc;
	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void getRecipesTest_invalid_parameter_fail() throws Exception {

		this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content("[]"))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void getRecipesTest_no_searchRequest_should_default_succeed() throws Exception {

		MvcResult result = this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content
						("{}"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String json = result.getResponse().getContentAsString();
		RecipeDTO[] recipeDTOS = objectMapper.readValue(json, RecipeDTO[].class);

		Assertions.assertEquals(4, recipeDTOS.length);
	}

	@Test
	void getRecipesTest_by_ID_should_return_recipe() throws Exception {

		String jsonStringRecipes = this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		RecipeDTO[] recipeDTOS = objectMapper.readValue(jsonStringRecipes, RecipeDTO[].class);
		UUID recipeId = recipeDTOS[0].getId();

		jsonStringRecipes = this.mockMvc.perform(get("/recipes/" + recipeId).contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		RecipeDTO recipeDTO = objectMapper.readValue(jsonStringRecipes, RecipeDTO.class);
		Assertions.assertEquals(recipeDTOS[0], recipeDTO);
	}

	@Test
	void getRecipesTest_searchRequest_recipe_by_name_should_return_recipe() throws Exception {

		SearchRequest searchRequest = SearchRequest.builder().build();
		List<Filter> filters = new ArrayList<>();
		Filter filter0 = Filter.builder()
				.key("name")
				.operation(":")
				.value("Chickpea Curry")
				.build();
		filters.add(filter0);

		searchRequest.setFilters(filters);

		String searchRequestJSONString = objectMapper.writeValueAsString(searchRequest);
		MvcResult mvcResult = this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content(searchRequestJSONString))
				.andExpect(status().isOk())
				.andReturn();
		String jsonString = mvcResult.getResponse().getContentAsString();
		RecipeDTO[] recipeDTOS = objectMapper.readValue(jsonString, RecipeDTO[].class);
		Assertions.assertEquals(1, recipeDTOS.length);
	}

	@Test
	void getRecipesTest_searchRequest_recipe_by_instructions_should_return_recipe() throws Exception {

		SearchRequest searchRequest = SearchRequest.builder().build();
		List<Filter> filters = new ArrayList<>();
		Filter filter0 = Filter.builder()
				.key("instructions")
				.operation(":")
				.value("vegetarian recipe instructions")
				.build();
		filters.add(filter0);

		searchRequest.setFilters(filters);

		String searchRequestJSONString = objectMapper.writeValueAsString(searchRequest);
		MvcResult mvcResult = this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content(searchRequestJSONString))
				.andExpect(status().isOk())
				.andReturn();
		String jsonString = mvcResult.getResponse().getContentAsString();
		RecipeDTO[] recipeDTOS = objectMapper.readValue(jsonString, RecipeDTO[].class);
		Assertions.assertEquals(1, recipeDTOS.length);
	}

	@Test
	void getRecipesTest_searchRequest_recipe_by_isVegetarian_should_return_recipes() throws Exception {

		SearchRequest searchRequest = SearchRequest.builder().build();
		List<Filter> filters = new ArrayList<>();
		Filter filter0 = Filter.builder()
				.key("isVegetarian")
				.operation(":")
				.value(true)
				.build();
		filters.add(filter0);

		searchRequest.setFilters(filters);

		String searchRequestJSONString = objectMapper.writeValueAsString(searchRequest);
		MvcResult mvcResult = this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content(searchRequestJSONString))
				.andExpect(status().isOk())
				.andReturn();
		String jsonString = mvcResult.getResponse().getContentAsString();
		RecipeDTO[] recipeDTOS = objectMapper.readValue(jsonString, RecipeDTO[].class);
		Assertions.assertEquals(2, recipeDTOS.length);
	}

	@Test
	void getRecipesTest_searchRequest_recipe_by_numberOfServings_should_return_recipe() throws Exception {

		SearchRequest searchRequest = SearchRequest.builder().build();
		List<Filter> filters = new ArrayList<>();
		Filter filter0 = Filter.builder()
				.key("numberOfServings")
				.operation(":")
				.value(2)
				.build();
		filters.add(filter0);

		searchRequest.setFilters(filters);

		String searchRequestJSONString = objectMapper.writeValueAsString(searchRequest);
		MvcResult mvcResult = this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content(searchRequestJSONString))
				.andExpect(status().isOk())
				.andReturn();
		String jsonString = mvcResult.getResponse().getContentAsString();
		RecipeDTO[] recipeDTOS = objectMapper.readValue(jsonString, RecipeDTO[].class);
		Assertions.assertEquals(1, recipeDTOS.length);
	}

	@Test
	void getRecipesTest_searchRequest_multiple_filters_isVegetarian_numberOfServings_should_return_some_recipes() throws Exception {

		SearchRequest searchRequest = SearchRequest.builder().build();
		List<Filter> filters = new ArrayList<>();
		Filter filter0 = Filter.builder()
				.key("isVegetarian")
				.operation(":")
				.value(false)
				.build();
		filters.add(filter0);

		Filter filter1 = Filter.builder()
				.key("numberOfServings")
				.operation(":")
				.value(2)
				.build();
		filters.add(filter1);

		searchRequest.setFilters(filters);

		String searchRequestJSONString = objectMapper.writeValueAsString(searchRequest);
		this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content(searchRequestJSONString))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void getRecipesTest_searchRequest_include_ingredients_should_return_recipe() throws Exception {

		SearchRequest searchRequest = SearchRequest.builder().build();
		List<Filter> filters = new ArrayList<>();
		Filter filter0 = Filter.builder()
				.key("recipeIngredients")
				.operation(":")
				.value("Garlic")
				.build();
		filters.add(filter0);

		Filter filter1 = Filter.builder()
				.key("recipeIngredients")
				.operation(":")
				.value("Beef")
				.build();
		filters.add(filter1);

		searchRequest.setFilters(filters);

		String searchRequestJSONString = objectMapper.writeValueAsString(searchRequest);
		MvcResult mvcResult = this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content(searchRequestJSONString))
				.andExpect(status().isOk())
				.andReturn();
		String jsonString = mvcResult.getResponse().getContentAsString();
		RecipeDTO[] recipeDTOS = objectMapper.readValue(jsonString, RecipeDTO[].class);
		Assertions.assertEquals(1, recipeDTOS.length);
	}

	@Test
	void getRecipesTest_searchRequest_exclude_ingredients_should_return_some_recipes() throws Exception {

		SearchRequest searchRequest = SearchRequest.builder().build();
		List<Filter> filters = new ArrayList<>();
		Filter filter0 = Filter.builder()
				.key("recipeIngredients")
				.operation("!:")
				.value("Beef")
				.build();
		filters.add(filter0);

		searchRequest.setFilters(filters);

		String searchRequestJSONString = objectMapper.writeValueAsString(searchRequest);
		MvcResult mvcResult = this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content(searchRequestJSONString))
				.andExpect(status().isOk())
				.andReturn();
		String jsonString = mvcResult.getResponse().getContentAsString();
		RecipeDTO[] recipeDTOS = objectMapper.readValue(jsonString, RecipeDTO[].class);
		Assertions.assertEquals(3, recipeDTOS.length);
	}

	@Test
	void getRecipesTest_searchRequest_multiple_filters_numberOfServings_ingredients_should_return_recipe() throws Exception {

		SearchRequest searchRequest = SearchRequest.builder().build();
		List<Filter> filters = new ArrayList<>();
		Filter filter0 = Filter.builder()
				.key("recipeIngredients")
				.operation(":")
				.value("Garlic")
				.build();
		filters.add(filter0);

		Filter filter1 = Filter.builder()
				.key("numberOfServings")
				.operation(":")
				.value(2)
				.build();
		filters.add(filter1);

		searchRequest.setFilters(filters);

		String searchRequestJSONString = objectMapper.writeValueAsString(searchRequest);
		MvcResult mvcResult = this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content(searchRequestJSONString))
				.andExpect(status().isOk())
				.andReturn();
		String jsonString = mvcResult.getResponse().getContentAsString();
		RecipeDTO[] recipeDTOS = objectMapper.readValue(jsonString, RecipeDTO[].class);
		Assertions.assertEquals(1, recipeDTOS.length);
	}

	@Test
	void getRecipesTest_searchRequest_multiple_filters_numberOfServings_ingredients_should_return_some_recipes() throws Exception {

		SearchRequest searchRequest = SearchRequest.builder().build();
		List<Filter> filters = new ArrayList<>();
		Filter filter0 = Filter.builder()
				.key("instructions")
				.operation(":")
				.value("instructions")
				.build();
		filters.add(filter0);

		Filter filter1 = Filter.builder()
				.key("recipeIngredients")
				.operation("!:")
				.value("Pizza Sauce")
				.build();
		filters.add(filter1);

		searchRequest.setFilters(filters);

		String searchRequestJSONString = objectMapper.writeValueAsString(searchRequest);
		MvcResult mvcResult = this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content(searchRequestJSONString))
				.andExpect(status().isOk())
				.andReturn();
		String jsonString = mvcResult.getResponse().getContentAsString();
		RecipeDTO[] recipeDTOS = objectMapper.readValue(jsonString, RecipeDTO[].class);
		Assertions.assertEquals(3, recipeDTOS.length);
	}

    @Test
    void getRecipesTest_searchRequest_include_and_exclude_ingredients_should_return_some_recipes() throws Exception {

		SearchRequest searchRequest = SearchRequest.builder().build();
		List<Filter> filters = new ArrayList<>();
		Filter filter0 = Filter.builder()
				.key("recipeIngredients")
				.operation(":")
				.value("Garlic")
				.build();
		filters.add(filter0);

		Filter filter1 = Filter.builder()
				.key("recipeIngredients")
				.operation("!:")
				.value("Chickpeas")
				.build();
		filters.add(filter1);

		searchRequest.setFilters(filters);

		String searchRequestJSONString = objectMapper.writeValueAsString(searchRequest);
		MvcResult mvcResult = this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content(searchRequestJSONString))
				.andExpect(status().isOk())
				.andReturn();
		String jsonString = mvcResult.getResponse().getContentAsString();
		RecipeDTO[] recipeDTOS = objectMapper.readValue(jsonString, RecipeDTO[].class);
		Assertions.assertEquals(3, recipeDTOS.length);
    }

	@Test
	void getRecipesTest_searchRequest_with_number_of_elements_set_should_return_some_recipes() throws Exception {

		SearchRequest searchRequest = SearchRequest.builder().build();
		searchRequest.setNumberOfElements(2);

		String searchRequestJSONString = objectMapper.writeValueAsString(searchRequest);
		MvcResult mvcResult = this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content(searchRequestJSONString))
				.andExpect(status().isOk())
				.andReturn();
		String jsonString = mvcResult.getResponse().getContentAsString();
		RecipeDTO[] recipeDTOS = objectMapper.readValue(jsonString, RecipeDTO[].class);
		Assertions.assertEquals(2, recipeDTOS.length);
	}

	@Test
	void getRecipesTest_searchRequest_order_by_name_ascending_should_return_some_recipes() throws Exception {

		SearchRequest searchRequest = SearchRequest.builder().build();
		searchRequest.setSortBy("name");

		String searchRequestJSONString = objectMapper.writeValueAsString(searchRequest);
		MvcResult mvcResult = this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content(searchRequestJSONString))
				.andExpect(status().isOk())
				.andReturn();
		String jsonString = mvcResult.getResponse().getContentAsString();
		RecipeDTO[] recipeDTOS = objectMapper.readValue(jsonString, RecipeDTO[].class);
		Assertions.assertEquals(4, recipeDTOS.length);

		String previous = "";

		for (final RecipeDTO current: recipeDTOS) {
			if (current.getName().compareTo(previous) < 0) {
				Assertions.fail();
			}
			previous = current.getName();
		}
	}
}
