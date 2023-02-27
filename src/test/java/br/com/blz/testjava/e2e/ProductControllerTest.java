package br.com.blz.testjava.e2e;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.blz.testjava.TestJavaApplication;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.model.WarehouseType;
import br.com.blz.testjava.service.ProductService;
import br.com.blz.testjava.share.ProductMock;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestJavaApplication.class)
@WebAppConfiguration
public class ProductControllerTest {

    private static final String RESOURCE_PATH = "/products";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ProductService productService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void save() throws Exception {
        Long spWarehouseQuantity = 12L;
        Long moemaWarehouseQuantity = 3L;
        Long sku = ProductMock.getRandomSku();

        Product product = ProductMock.createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        mockMvc
            .perform(
                post(RESOURCE_PATH)
                    .contentType("application/json")
                    .content(convertProductMockToString(product)))
            .andExpect(status().isCreated());
    }

    @Test
    public void findBySku() throws Exception {
        Long spWarehouseQuantity = 12L;
        Long moemaWarehouseQuantity = 3L;
        Long sku = ProductMock.getRandomSku();

        Product product = ProductMock.createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        mockMvc
            .perform(
                post(RESOURCE_PATH)
                    .contentType("application/json")
                    .content(convertProductMockToString(product)))
            .andExpect(status().isCreated());

        String searchUrl = RESOURCE_PATH + "/" + sku;

        mockMvc
            .perform(get(searchUrl))
            .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestExceptionWhenTryCreateAPProductWhiteoutMandatoryAttributes() throws Exception {
        Long spWarehouseQuantity = 12L;
        Long moemaWarehouseQuantity = 3L;
        Long sku = ProductMock.getRandomSku();

        Product product = ProductMock.createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);
        product.setName(null);

        mockMvc
            .perform(
                post(RESOURCE_PATH)
                    .contentType("application/json")
                    .content(convertProductMockToString(product)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnResourceNotFoundWhenGivenSkuIsNotFound() throws Exception {
        String searchUrl = RESOURCE_PATH + "/0";

        mockMvc
            .perform(get(searchUrl))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProduct() throws Exception {
        Long spWarehouseQuantity = 12L;
        Long moemaWarehouseQuantity = 3L;
        Long totalProductQuantity = spWarehouseQuantity + moemaWarehouseQuantity;

        Long sku = ProductMock.getRandomSku();

        Product product = ProductMock.createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        mockMvc
            .perform(
                post(RESOURCE_PATH)
                    .contentType("application/json")
                    .content(convertProductMockToString(product)))
            .andExpect(status().isCreated());

        Optional<Product> persisted = productService.findBySku(sku);

        // asserting initial product state
        assertTrue(persisted.isPresent());
        assertEquals(sku, persisted.get().getSku());
        assertEquals(totalProductQuantity, persisted.get().getInventory().getQuantity());

        // updating product
        Product productToUpdate = persisted.get();
        productToUpdate.setName("Updated Product Name");

        Long updatedSpWarehouseQuantity = 20L;
        Long updatedMoemaWarehouseQuantity = 10L;
        Long updatedTotalProductQuantity = updatedSpWarehouseQuantity + updatedMoemaWarehouseQuantity;

        Warehouse spWarehouse = new Warehouse(
            "SP",
            updatedSpWarehouseQuantity,
            WarehouseType.ECOMMERCE
        );

        Warehouse moemaWarehouse = new Warehouse(
            "MOEMA",
            updatedMoemaWarehouseQuantity,
            WarehouseType.PHYSICAL_STORE
        );

        List<Warehouse> warehouses = new ArrayList<>(2);
        warehouses.add(spWarehouse);
        warehouses.add(moemaWarehouse);

        Inventory updatedInventory = new Inventory(warehouses);
        productToUpdate.setInventory(updatedInventory);

        String putUrl = RESOURCE_PATH + "/" + sku;

        mockMvc
            .perform(
                put(putUrl)
                    .contentType("application/json")
                    .content(convertProductMockToString(productToUpdate)))
            .andExpect(status().isOk());

        Optional<Product> persistedAfterUpdate = productService.findBySku(sku);

        // asserting updated product
        assertTrue(persistedAfterUpdate.isPresent());
        assertEquals(sku, persistedAfterUpdate.get().getSku());
        assertEquals("Updated Product Name", persistedAfterUpdate.get().getName());
        assertEquals(updatedTotalProductQuantity, persistedAfterUpdate.get().getInventory().getQuantity());
    }

    @Test
    public void shouldReturnResourceNotFoundWhenTryUpdateProductWithNonExistingSku() throws Exception {
        Long spWarehouseQuantity = 12L;
        Long moemaWarehouseQuantity = 3L;
        Long totalProductQuantity = spWarehouseQuantity + moemaWarehouseQuantity;

        Long sku = ProductMock.getRandomSku();

        Product product = ProductMock.createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        mockMvc
            .perform(
                post(RESOURCE_PATH)
                    .contentType("application/json")
                    .content(convertProductMockToString(product)))
            .andExpect(status().isCreated());

        Optional<Product> persisted = productService.findBySku(sku);

        // asserting initial product state
        assertTrue(persisted.isPresent());
        assertEquals(sku, persisted.get().getSku());
        assertEquals(totalProductQuantity, persisted.get().getInventory().getQuantity());

        // updating product
        Product productToUpdate = new Product(0L, product.getName(), product.getInventory());
        String putUrl = RESOURCE_PATH + "/" + productToUpdate.getSku();

        mockMvc
            .perform(
                put(putUrl)
                    .contentType("application/json")
                    .content(convertProductMockToString(productToUpdate)))
            .andExpect(status().isNotFound());
    }

    @Test
    public void deleteProduct() throws Exception {
        Long spWarehouseQuantity = 12L;
        Long moemaWarehouseQuantity = 3L;
        Long sku = ProductMock.getRandomSku();

        Product product = ProductMock.createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        mockMvc
            .perform(
                post(RESOURCE_PATH)
                    .contentType("application/json")
                    .content(convertProductMockToString(product)))
            .andExpect(status().isCreated());

        Optional<Product> persisted = productService.findBySku(sku);

        assertTrue(persisted.isPresent());

        String deleteUrl = RESOURCE_PATH + "/" + sku;

        mockMvc
            .perform(delete(deleteUrl))
            .andExpect(status().isAccepted());

        Optional<Product> deletedProduct = productService.findBySku(sku);

        assertFalse(deletedProduct.isPresent());
    }

    public String convertProductMockToString(Product product) throws Exception {
        return objectMapper.writeValueAsString(product);
    }

}
