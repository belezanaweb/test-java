package br.com.blz.testjava.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.blz.testjava.controller.handler.ResponseObject;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.repository.ProductRepositoryImpl;
import br.com.blz.testjava.service.ProductService;
import br.com.blz.testjava.templates.ProductTemplate;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ProductControllerTest {

    @Autowired 
    protected MockMvc mvc;
    
    @Autowired
    private JsonUtil jsonUtil;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductRepositoryImpl productRepository;
    
    public static final String BASE_URL = "http://localhost:8080/products";
    
    private final ProductTemplate productTemplate = ProductTemplate.getInstance();
    
    @AfterEach
    public void afterTest() {
        productRepository.drop();
    }
    
    @Test
    public void getProductSuccess() throws Exception {

      final ProductDTO product = productTemplate.getValid();      
      ProductDTO productSave = productService.save(product);
      
      final MvcResult mvcResult =
          mvc.perform(
                  MockMvcRequestBuilders.get(BASE_URL + "/" + product.getSku())
                  .contentType(MediaType.APPLICATION_JSON_VALUE))
                  .andExpect(status().isOk())
                  .andReturn();
      
      final ProductDTO response = jsonUtil.fromJson(mvcResult.getResponse().getContentAsString(), ProductDTO.class);
      Assert.assertEquals(response.getSku(), productSave.getSku());
    }
    
    @Test
    public void saveProductSuccess() throws Exception {
        
        final ProductDTO product = productTemplate.getValid();
        final MvcResult mvcResult =
            mvc.perform(
                    MockMvcRequestBuilders.post(BASE_URL)
                    .content(jsonUtil.toJson(product))
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isCreated())
                    .andReturn();
        
        ProductDTO productGet = productService.getBySku(product.getSku());
        
        final ProductDTO response = jsonUtil.fromJson(mvcResult.getResponse().getContentAsString(), ProductDTO.class);
        Assert.assertEquals(response.getSku(), productGet.getSku());
        
    }
    
    @Test
    public void saveProductFail() throws Exception {
                
        final ProductDTO product = productTemplate.getValid();
        
        productService.save(product);
        
        final MvcResult mvcResult =
            mvc.perform(
                    MockMvcRequestBuilders.post(BASE_URL)
                    .content(jsonUtil.toJson(product))
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().is4xxClientError())
                    .andReturn();
                
        final ResponseObject response = jsonUtil.fromJson(mvcResult.getResponse().getContentAsString(), ResponseObject.class);
        Assert.assertEquals(response.getError().getMessage(), "Dois produtos sao considerados iguais se os seus skus forem iguais");
        
    }
    
    @Test
    public void updateProductSuccess() throws Exception {
              
        ProductDTO productSave = productService.save(productTemplate.getValid());
        ProductDTO productUpdate = productTemplate.getValid(); 
        productUpdate.setName("UPDATED");
        
        final MvcResult mvcResult =
            mvc.perform(
                    MockMvcRequestBuilders.put(BASE_URL + "/" + productSave.getSku())
                    .content(jsonUtil.toJson(productUpdate))
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();
                
        final ProductDTO response = jsonUtil.fromJson(mvcResult.getResponse().getContentAsString(), ProductDTO.class);
        Assert.assertEquals(response.getName(), productUpdate.getName());
        
    }
    
    @Test
    public void updateProductFail() throws Exception {
                
        final ProductDTO product = productTemplate.getValid();
                
        final MvcResult mvcResult =
                mvc.perform(
                        MockMvcRequestBuilders.put(BASE_URL + "/" + product.getSku())
                        .content(jsonUtil.toJson(product))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().is4xxClientError())
                        .andReturn();
                
        final ResponseObject response = jsonUtil.fromJson(mvcResult.getResponse().getContentAsString(), ResponseObject.class);
        Assert.assertEquals(response.getError().getMessage(), "Produto nao encontrado");
        
    }
    
    @Test
    public void deleteProductSuccess() throws Exception {
        
        ProductDTO productSave = productService.save(productTemplate.getValid());
        mvc.perform(
                    MockMvcRequestBuilders.delete(BASE_URL + "/" + productSave.getSku())
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();
        
    }
    
    @Test
    public void deleteProductFail() throws Exception {
                
        final ProductDTO product = productTemplate.getValid();
                
        final MvcResult mvcResult =
                mvc.perform(
                        MockMvcRequestBuilders.delete(BASE_URL + "/" + product.getSku())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().is4xxClientError())
                        .andReturn();
                
        final ResponseObject response = jsonUtil.fromJson(mvcResult.getResponse().getContentAsString(), ResponseObject.class);
        Assert.assertEquals(response.getError().getMessage(), "Produto nao encontrado");
        
    }
    
}
