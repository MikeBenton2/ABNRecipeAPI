package com.abn.recipeapi_v1;

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

//    @Test
//    void getRecipesTest_succeed() throws Exception {
//
//        ResponseEntity<GetRecipes200Response> entity = new ResponseEntity<>(HttpStatus.OK);
//
//        when(service.getRecipes(0,0, null)).thenReturn(entity);
//
//        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//        parameters.add("page", "0");
//        parameters.add("per_page", "10");
//
//        this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).queryParams(parameters))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getRecipesTest_missing_page_parameter_fail() throws Exception {
//
//        ResponseEntity<GetRecipes200Response> entity = new ResponseEntity<>(HttpStatus.OK);
//
//        when(service.getRecipes(0,0, null)).thenReturn(entity);
//
//        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//        parameters.add("per_page", "10");
//
//        this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).queryParams(parameters))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void getRecipesTest_missing_perPage_parameter_fail() throws Exception {
//
//        ResponseEntity<GetRecipes200Response> entity = new ResponseEntity<>(HttpStatus.OK);
//
//        when(service.getRecipes(0,0, null)).thenReturn(entity);
//
//        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//        parameters.add("page", "0");
//
//        this.mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON).queryParams(parameters))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
}
