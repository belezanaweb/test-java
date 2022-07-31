package br.com.blz.testjava;

import br.com.blz.testjava.controller.ProductController;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductControllerTest {

    @Autowired
    ProductController productController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public void setup() {
        System.out.printf("Populating database...");
        Product product1 = new Product();
        product1.setSku(1);
        product1.setName("perfume 1");

        Inventory inventory1 = new Inventory();
        Warehouse warehouse1 = new Warehouse();
        Warehouse warehouse2 = new Warehouse();

        warehouse1.setLocalilty("Rio de Janeiro");
        warehouse1.setType(Warehouse.Type.ECOMMERCE);
        warehouse1.setQuantity(3);

        warehouse2.setLocalilty("São Paulo");
        warehouse2.setType(Warehouse.Type.PHYSICAL_STORE);
        warehouse2.setQuantity(8);

        inventory1.setWarehouses(Arrays.asList(warehouse1, warehouse2));

        product1.setInventory(inventory1);

        productController.createProduct(product1);

        System.out.printf("Created records for the test");
        Product product2 = new Product();
        product2.setSku(2);
        product2.setName("perfume 1");

        Inventory inventory2 = new Inventory();
        Warehouse warehouse21 = new Warehouse();
        Warehouse warehouse22 = new Warehouse();

        warehouse21.setLocalilty("Curituba");
        warehouse21.setType(Warehouse.Type.ECOMMERCE);
        warehouse21.setQuantity(6);

        warehouse22.setLocalilty("Leicester");
        warehouse22.setType(Warehouse.Type.PHYSICAL_STORE);
        warehouse22.setQuantity(2);

        inventory2.setWarehouses(Arrays.asList(warehouse21, warehouse22));

        product2.setInventory(inventory2);

        productController.createProduct(product2);
    }

    @Test
    public void contextLoads() {
        Assertions.assertThat(productController).isNotNull();
    }

    @Test
    public void testGetProductById() {

        try {
            mockMvc.perform(get("/products/2")).andExpect(status().isOk()).andExpect(content().json("{\"sku\":2,\"name\":\"perfume 1\",\"isMarketable\":true,\"inventory\":{\"quantity\":8,\"warehouses\":[{\"localilty\":\"Curituba\",\"quantity\":6,\"type\":\"ECOMMERCE\"},{\"localilty\":\"Leicester\",\"quantity\":2,\"type\":\"PHYSICAL_STORE\"}]}}"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testCreateProduct() {

        try {

            JSONObject json = new JSONObject("{\"sku\":6,\"name\":\"perfume 6\",\"isMarketable\":true,\"inventory\":{\"quantity\":11,\"warehouses\":[{\"localilty\":\"Rio de Janeiro\",\"quantity\":3,\"type\":\"ECOMMERCE\"},{\"localilty\":\"São Paulo\",\"quantity\":8,\"type\":\"PHYSICAL_STORE\"}]}}");
            mockMvc.perform(post("/products").contentType("application/json; charset=utf8").content(json.toString())).andExpect(status().isOk());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testCreateProductAlreadyRegistered() {

        try {

            JSONObject json = new JSONObject("{\"sku\":6,\"name\":\"perfume 1\",\"isMarketable\":true,\"inventory\":{\"quantity\":11,\"warehouses\":[{\"localilty\":\"Rio de Janeiro\",\"quantity\":3,\"type\":\"ECOMMERCE\"},{\"localilty\":\"São Paulo\",\"quantity\":8,\"type\":\"PHYSICAL_STORE\"}]}}");
            mockMvc.perform(post("/products").contentType("application/json; charset=utf8").content(json.toString())).andExpect(status().is(409));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    @Test
    public void testUpdateProduct() {

        try {

            JSONObject json = new JSONObject("{\"sku\":1,\"name\":\"perfume 1 ALTERADO\",\"isMarketable\":true,\"inventory\":{\"quantity\":11,\"warehouses\":[{\"localilty\":\"Rio de Janeiro\",\"quantity\":3,\"type\":\"ECOMMERCE\"},{\"localilty\":\"São Paulo\",\"quantity\":8,\"type\":\"PHYSICAL_STORE\"}]}}");
            mockMvc.perform(put("/products/1/").contentType("application/json; charset=utf8").content(json.toString())).andExpect(status().isOk());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testDeleteProduct() {

        try {
            mockMvc.perform(delete("/products/1")).andExpect(status().isOk());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
