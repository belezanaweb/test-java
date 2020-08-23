package br.com.blz.testjava.controllers;

import br.com.blz.testjava.utils.TestUtils;
import br.com.blz.testjava.dtos.ProductDTO;
import br.com.blz.testjava.exceptions.SkuAlreadyExistsException;
import br.com.blz.testjava.exceptions.SkuNotFoundException;
import br.com.blz.testjava.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    void whenCreateProduct_ValidJson_ThenReturn201() throws Exception {
        ProductDTO productDTO = TestUtils.buildProduct();

        mockMvc.perform(post("/v1/products")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isCreated());
        verify(productService).createProduct(productDTO);
    }

    @Test
    void whenCreateProduct_InvalidJson_ThenReturn201() throws Exception {
        mockMvc.perform(post("/v1/products")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString("invalid")))
                .andExpect(status().isBadRequest());
        verify(productService, never()).createProduct(any());
    }

    @Test
    void whenCreateProduct_InvalidName_ThenReturn400() throws Exception {
        ProductDTO productDTO = TestUtils.buildProduct();
        productDTO.setName(null);

        mockMvc.perform(post("/v1/products")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(productDTO)))
            .andExpect(status().isBadRequest());
        verify(productService, never()).createProduct(productDTO);
    }

    @Test
    void whenCreateProduct_AlreadyExists_ThenReturn400() throws Exception {
        ProductDTO productDTO = TestUtils.buildProduct();
        doThrow(SkuAlreadyExistsException.class).when(productService).createProduct(productDTO);

        mockMvc.perform(post("/v1/products")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(productDTO)))
            .andExpect(status().isBadRequest());
        verify(productService).createProduct(productDTO);
    }

    @Test
    void whenCreateProduct_InvalidSku_ThenReturn400() throws Exception {
        ProductDTO productDTO = TestUtils.buildProduct();
        productDTO.setSku(null);

        mockMvc.perform(post("/v1/products")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(productDTO)))
            .andExpect(status().isBadRequest());
        verify(productService, never()).createProduct(productDTO);
    }

    @Test
    void whenCreateProduct_InvalidInventory_ThenReturn400() throws Exception {
        ProductDTO productDTO = TestUtils.buildProduct();
        productDTO.setInventory(null);

        mockMvc.perform(post("/v1/products")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(productDTO)))
            .andExpect(status().isBadRequest());
        verify(productService, never()).createProduct(productDTO);
    }

    @Test
    void whenCreateProduct_InvalidWarehouseLocality_ThenReturn400() throws Exception {
        ProductDTO productDTO = TestUtils.buildProduct();
        productDTO.getInventory().getWarehouses().get(0).setLocality(null);

        mockMvc.perform(post("/v1/products")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(productDTO)))
            .andExpect(status().isBadRequest());
        verify(productService, never()).createProduct(productDTO);
    }

    @Test
    void whenGetProduct_ValidId_ThenReturn200() throws Exception {
        mockMvc.perform(get("/v1/products/123")
            .contentType("application/json"))
            .andExpect(status().isOk());
        verify(productService).getProduct(123);
    }

    @Test
    void whenGetProduct_InvalidId_ThenReturn404() throws Exception {
        doThrow(SkuNotFoundException.class).when(productService).getProduct(123);
        mockMvc.perform(get("/v1/products/123")
            .contentType("application/json"))
            .andExpect(status().isNotFound());
        verify(productService).getProduct(123);
    }

    @Test
    void whenUpdateProduct_ValidId_ThenReturn200() throws Exception {
        ProductDTO productDTO = TestUtils.buildProduct();

        mockMvc.perform(put("/v1/products/12345")
            .contentType("application/json")
        .content(objectMapper.writeValueAsString(productDTO)))
            .andExpect(status().isOk());
        verify(productService).updateProduct(12345L, productDTO);
    }

    @Test
    void whenUpdateProduct_InvalidId_ThenReturn404() throws Exception {
        ProductDTO productDTO = TestUtils.buildProduct();
        doThrow(SkuNotFoundException.class).when(productService).updateProduct(12345L, productDTO);

        mockMvc.perform(put("/v1/products/12345")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(productDTO)))
            .andExpect(status().isNotFound());
        verify(productService).updateProduct(12345L, productDTO);
    }

    @Test
    void whenDeleteProduct_ValidId_ThenReturn204() throws Exception {
        mockMvc.perform(delete("/v1/products/12345")
            .contentType("application/json"))
            .andExpect(status().isNoContent());
        verify(productService).deleteProduct(12345L);
    }

    @Test
    void whenDeleteProduct_InvalidId_ThenReturn400() throws Exception {
        doThrow(SkuNotFoundException.class).when(productService).deleteProduct(12345L);

        mockMvc.perform(delete("/v1/products/12345")
            .contentType("application/json"))
            .andExpect(status().isNotFound());
        verify(productService).deleteProduct(12345L);
    }

}
