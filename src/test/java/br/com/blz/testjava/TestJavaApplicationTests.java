package br.com.blz.testjava;

import static br.com.blz.testjava.mother.ProductRequestMother.getProductRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.blz.testjava.controller.request.ProductRequest;
import br.com.blz.testjava.controller.request.WarehouseRequest;
import br.com.blz.testjava.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestJavaApplicationTests {
    
    private static final String PRODUCTS = "/products";
    private static final String PRODUCT_NOT_FOUND = "Product with sku [%d] not found!";
    private static final String PRODUCT_ALREADY_EXISTENT = "Product with sku [%d] already exists!";
    
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper mapper;
    
    @Autowired
    private ProductService productService;
    
    @Before
    public void beforeTest() {
        productService.clean();
    }
    
    @Test
    public void testSaveProduct_Succeeds() throws Exception {
        ProductRequest product = getProductRequest(1L);
        String content = mapper.writeValueAsString(product);
        
        mvc.perform(post(PRODUCTS).content(content).contentType(MediaType.APPLICATION_JSON_UTF8))
            
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.sku").value(product.getSku()))
            .andExpect(jsonPath("$.name").value(product.getName()))
            .andExpect(jsonPath("$.inventory.warehouses[0].locality")
                .value(product.getInventory().getWarehouses().get(0).getLocality()))
            .andExpect(jsonPath("$.inventory.warehouses[0].quantity")
                .value(product.getInventory().getWarehouses().get(0).getQuantity()))
            .andExpect(jsonPath("$.inventory.warehouses[0].type")
                .value(product.getInventory().getWarehouses().get(0).getType()));
    }
    
    @Test
    public void testSaveProduct_FailsWhen_SameSku() throws Exception {
        ProductRequest product = getProductRequest(1L);
        String content = mapper.writeValueAsString(product);
        
        mvc.perform(post(PRODUCTS).content(content).contentType(MediaType.APPLICATION_JSON_UTF8))
            
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.sku").value(product.getSku()))
            .andExpect(jsonPath("$.name").value(product.getName()))
            .andExpect(jsonPath("$.inventory.warehouses[0].locality")
                .value(product.getInventory().getWarehouses().get(0).getLocality()))
            .andExpect(jsonPath("$.inventory.warehouses[0].quantity")
                .value(product.getInventory().getWarehouses().get(0).getQuantity()))
            .andExpect(jsonPath("$.inventory.warehouses[0].type")
                .value(product.getInventory().getWarehouses().get(0).getType()));
        
        mvc.perform(post(PRODUCTS).content(content).contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.message")
                .value(String.format(PRODUCT_ALREADY_EXISTENT, product.getSku())));
    }
    
    @Test
    public void testUpdateProduct_Succeeds() throws Exception {
        ProductRequest product = getProductRequest(1L);
        String content = mapper.writeValueAsString(product);
        
        mvc.perform(post(PRODUCTS).content(content).contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isCreated());
        
        product.setName("Updated Name");
        mvc.perform(put(PRODUCTS).content(mapper.writeValueAsString(product))
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.sku").value(product.getSku()))
            .andExpect(jsonPath("$.name").value(product.getName()))
            .andExpect(jsonPath("$.inventory.warehouses[0].locality")
                .value(product.getInventory().getWarehouses().get(0).getLocality()))
            .andExpect(jsonPath("$.inventory.warehouses[0].quantity")
                .value(product.getInventory().getWarehouses().get(0).getQuantity()))
            .andExpect(jsonPath("$.inventory.warehouses[0].type")
                .value(product.getInventory().getWarehouses().get(0).getType()));
    }
    
    @Test
    public void testUpdateProduct_FailsWhen_ProductNotExistent() throws Exception {
        ProductRequest product = getProductRequest(1L);
        
        mvc.perform(put(PRODUCTS).content(mapper.writeValueAsString(product))
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound())
            .andExpect(
                jsonPath("$.message").value(String.format(PRODUCT_NOT_FOUND, product.getSku())));
    }
    
    @Test
    public void testGetProduct_Succeeds() throws Exception {
        ProductRequest product = getProductRequest(1L);
        String content = mapper.writeValueAsString(product);
        
        mvc.perform(post(PRODUCTS).content(content).contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isCreated());
        
        mvc.perform(get(PRODUCTS + "/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.sku").value(product.getSku()))
            .andExpect(jsonPath("$.name").value(product.getName()))
            .andExpect(jsonPath("$.inventory.quantity")
                .value(product.getInventory().getWarehouses().stream().mapToInt(
                    WarehouseRequest::getQuantity).sum()))
            .andExpect(jsonPath("$.isMarketable").value(true))
            .andExpect(jsonPath("$.inventory.warehouses[0].locality")
                .value(product.getInventory().getWarehouses().get(0).getLocality()))
            .andExpect(jsonPath("$.inventory.warehouses[0].quantity")
                .value(product.getInventory().getWarehouses().get(0).getQuantity()))
            .andExpect(jsonPath("$.inventory.warehouses[0].type")
                .value(product.getInventory().getWarehouses().get(0).getType()));
    }
    
    @Test
    public void testGetProduct_FailsWhen_ProductNotExistent() throws Exception {
        mvc.perform(get(PRODUCTS + "/1"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").value(String.format(PRODUCT_NOT_FOUND, 1L)));
    }
    
    @Test
    public void testDeleteProduct_Succeeds() throws Exception {
        ProductRequest product = getProductRequest(1L);
        String content = mapper.writeValueAsString(product);
        
        mvc.perform(post(PRODUCTS).content(content).contentType(MediaType.APPLICATION_JSON_UTF8))
            
            .andExpect(status().isCreated());
        
        mvc.perform(delete(PRODUCTS + "/1"))
            .andExpect(status().isOk());
        
        mvc.perform(get(PRODUCTS + "/1")).andExpect(status().isNotFound());
    }
    
    @Test
    public void testDeleteProduct_FailsWhen_ProductNotExistent() throws Exception {
        mvc.perform(delete(PRODUCTS + "/1"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").value(String.format(PRODUCT_NOT_FOUND, 1L)));
        
    }
    
}
