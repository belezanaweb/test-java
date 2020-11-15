package br.com.blz.testjava.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.repositories.ProductRepository;
import br.com.blz.testjava.service.ProductService;

@WebMvcTest
class GetProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ProductRepository repository;
    
    
    @SpyBean
    private ProductService service;
    
    @Test
    void getProduct() throws Exception {
        
         var product = new Product();
         product.setId(1L);
         product.setSku(12L);
         product.setName("banana");
         
         Mockito.when(repository.findBySku(12L)).thenReturn(product);
         
        this.mockMvc.perform(MockMvcRequestBuilders.get("/product/{sku}",12).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(product.getName()));

    }
    
    @Test
    void getNonExistentProduct() throws Exception {
         
         Mockito.when(repository.findBySku(12L)).thenReturn(null);
         
        this.mockMvc.perform(MockMvcRequestBuilders.get("/product/{sku}",12).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        ;

    }
    
    @Test
    void getProductInternalError() throws Exception {
         
         Mockito.when(repository.findBySku(12L)).thenThrow(NullPointerException.class);
         
        this.mockMvc.perform(MockMvcRequestBuilders.get("/product/{sku}",12).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isInternalServerError())
        ;

    }

}
