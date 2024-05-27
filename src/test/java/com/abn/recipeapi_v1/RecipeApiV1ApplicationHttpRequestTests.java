package com.abn.recipeapi_v1;

import com.abn.recipeapi_v1.model.SearchRequest;
import com.abn.recipeapi_v1.model.RecipeDTO;
import com.abn.recipeapi_v1.services.RecipeDAOService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RecipeApiV1ApplicationHttpRequestTests {

    @MockBean
    private RecipeDAOService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRecipesTest_no_searchRequest_should_default_succeed() throws Exception {

        ResponseEntity<List<RecipeDTO>> entity = new ResponseEntity<>(HttpStatus.OK);
        SearchRequest searchRequest = new SearchRequest();

        when(service.getRecipes(searchRequest)).thenReturn(entity);

        this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getRecipesTest_invalid_parameter_fail() throws Exception {

        ResponseEntity<List<RecipeDTO>> entity = new ResponseEntity<>(HttpStatus.OK);
        SearchRequest searchRequest = new SearchRequest();

        when(service.getRecipes(searchRequest)).thenReturn(entity);

        this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).content("[]"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
