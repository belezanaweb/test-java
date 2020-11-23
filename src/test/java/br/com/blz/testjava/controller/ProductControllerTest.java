package br.com.blz.testjava.controller;

import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Constants;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.blz.testjava.TestUtils.getProductDto;
import static br.com.blz.testjava.TestUtils.getProductPayload;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    private static final String END_POINT = Constants.API_V_1 + "/product";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService service;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() throws Exception {
        given(service.create(any(ProductDto.class))).willAnswer(invocation -> invocation.getArgument(0));

        mvc.perform(post(END_POINT).contentType(APPLICATION_JSON).content(getProductPayload()))
           .andExpect(status().isCreated())
           .andExpect(jsonPath("$", aMapWithSize(4)))
           .andExpect(jsonPath("$.sku", is(43264)))
           .andExpect(jsonPath("$.name", is("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")))
           .andExpect(jsonPath("$.inventory.warehouses", hasSize(2)))
           .andExpect(jsonPath("$.inventory.warehouses[0].locality", is("SP")))
           .andExpect(jsonPath("$.inventory.warehouses[0].quantity", is(12)))
           .andExpect(jsonPath("$.inventory.warehouses[0].type", is("ECOMMERCE")));

        verify(service).create(any(ProductDto.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void create_WithoutRequiredFields() throws Exception {
        mvc.perform(post(END_POINT).contentType(APPLICATION_JSON).content("{}"))
           .andExpect(status().isBadRequest())
           .andExpect(jsonPath("$.errorMsg", is("productDto -> [id: sku não deve ser nulo, name: não deve estar vazio]")));

        verifyNoInteractions(service);
    }

    @Test
    void update() throws Exception {
        given(service.update(anyLong(), any(ProductDto.class))).willAnswer(invocation -> invocation.getArgument(1));

        mvc.perform(put(END_POINT + "/{id}", 1L).contentType(APPLICATION_JSON).content(getProductPayload()))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.sku", is(43264)))
           .andExpect(jsonPath("$.name", is("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")))
           .andExpect(jsonPath("$.inventory.warehouses", hasSize(2)))
           .andExpect(jsonPath("$.inventory.warehouses[0].locality", is("SP")))
           .andExpect(jsonPath("$.inventory.warehouses[0].quantity", is(12)))
           .andExpect(jsonPath("$.inventory.warehouses[0].type", is("ECOMMERCE")));

        verify(service).update(anyLong(), any(ProductDto.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void update_WithoutRequiredFields() throws Exception {
        mvc.perform(put(END_POINT + "/{id}", 1L).contentType(APPLICATION_JSON).content("{}"))
           .andExpect(status().isBadRequest())
           .andExpect(jsonPath("$.errorMsg", is("productDto -> [name: não deve estar vazio]")));

        verifyNoInteractions(service);
    }

    @Test
    void getById() throws Exception {
        given(service.findById(anyLong())).willReturn(Optional.of(getProductDto(1L, "Loreal")));

        mvc.perform(get(END_POINT + "/1"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", aMapWithSize(4)))
           .andExpect(jsonPath("$.sku", is(1)))
           .andExpect(jsonPath("$.name", is("Loreal")));

        verify(service).findById(anyLong());
        verifyNoMoreInteractions(service);
    }

    @Test
    void getById_NoExists() throws Exception {
        mvc.perform(get(END_POINT + "/1")).andExpect(status().isNotFound());

        verify(service).findById(anyLong());
        verifyNoMoreInteractions(service);
    }


    @Test
    void deleteById() throws Exception {
        given(service.deleteById(anyLong())).willReturn(Optional.of(true));

        mvc.perform(delete(END_POINT + "/1"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", is(true)));

        verify(service).deleteById(anyLong());
        verifyNoMoreInteractions(service);
    }

}
