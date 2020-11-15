package br.com.blz.testjava.controllers;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.dtos.CreateProductRequest;
import br.com.blz.testjava.dtos.CreateProductRequest.CreateProductInventory;
import br.com.blz.testjava.dtos.Warehouse;
import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.repositories.ProductRepository;
import br.com.blz.testjava.service.ProductService;

@WebMvcTest
class CreateProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ProductRepository repository;
    
    @SpyBean
    private ProductService service;
    
    @Test
    void createProduct() throws Exception {
        when(this.repository.save(any(Product.class))).thenReturn(getCreatedProduct());
        
        String content = getJSONBody(getCreateProductRequest());
        this.mockMvc.perform(
                post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                )
            .andExpect(status().isCreated())
        ;
    }
    
    @Test
    void createProductWithoutSKU() throws Exception {
        var requestObject = getCreateProductRequest();
        requestObject.setSku(null);
        var content = getJSONBody(requestObject);
        
        this.mockMvc.perform(
                post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                )
            .andExpect(status().isBadRequest())
            .andExpect(MockMvcResultMatchers.content().string("SKU é obrigatório"))
        ;
    }
    
    
    private String getJSONBody(CreateProductRequest requestObject) throws JsonProcessingException {
       var mapper = new ObjectMapper();
 
      return mapper.writeValueAsString(requestObject);
    }
    private CreateProductRequest getCreateProductRequest() {
       var product = new CreateProductRequest();
       product.setName("product1");
       product.setSku(1L);
       product.setInventory(getInventory());
       return product;
    }


    private CreateProductInventory getInventory() {
        var inventory = new CreateProductInventory();
        inventory.setWarehouses(createWarehouses());
        return inventory;
    }


    private List<Warehouse> createWarehouses(){
        var warehouses = new ArrayList<Warehouse>();
        var warehouse1 = new Warehouse();
        warehouse1.setLocality("ES");
        warehouse1.setQuantity(2);
        warehouse1.setType("storage");
        var warehouse2 = new Warehouse();
        warehouse2.setLocality("RJ");
        warehouse2.setQuantity(3);
        warehouse2.setType("storage");
        warehouses.add(warehouse1);
        warehouses.add(warehouse2);
        return warehouses;

    }
    
    private Product getCreatedProduct() {
        var product = new Product();
        new ModelMapper().map(getCreateProductRequest(), product);
        
        product.setId(1L);
        return product;
    }

}
