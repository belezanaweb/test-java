package br.com.blz.testjava.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import br.com.blz.testjava.json.JsonProduct;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.WarehouseDTO;

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
        productController.createProduct(buildProductDTO());
    }

    @Test
    public void createProductOk() throws Exception {
        JSONObject json = JsonProduct.ok;
        mockMvc.perform(post("/products")
                .contentType("application/json; charset=utf8")
                .content(json.toString()))
            .andExpect(status().isOk());
    }

    @Test
    public void createProductWithBadRequest() throws Exception {
        JSONObject json = JsonProduct.notFound;
        mockMvc.perform(post("/products")
                .contentType("application/json; charset=utf8")
                .content(json.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void createProductAlreadyExist() throws Exception {
        JSONObject json = JsonProduct.alreadyExist;
        mockMvc.perform(post("/products")
                .contentType("application/json; charset=utf8")
                .content(json.toString()))
            .andExpect(status().isConflict());
    }

    @Test
    public void recoveryProduct() throws Exception {
        mockMvc.perform(get("/products/1564"))
            .andExpect(status().isOk());
    }

    @Test
    public void recoveryProductNotFound() throws Exception {
        mockMvc.perform(get("/products/15"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void inventoryQuantity() throws Exception {
        mockMvc.perform(get("/products/1564"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.inventory.quantity", is(3)));
    }

    @Test
    public void isMarketableIsTrue() throws Exception {
        mockMvc.perform(get("/products/1564"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.isMarketable", is(true)));
    }

    @Test
    public void updateProduct() throws Exception {
        JSONObject json = JsonProduct.update;
        mockMvc.perform(put("/products/1564")
                .contentType("application/json; charset=utf8")
                .content(json.toString()))
            .andExpect(status().isOk());
    }

    @Test
    public void deleteProduct() throws Exception {
        mockMvc.perform(delete("/products/1564"))
            .andExpect(status().isOk());
    }

    private ProductDTO buildProductDTO() {
        WarehouseDTO wareHouseEccomerce = new WarehouseDTO();
        wareHouseEccomerce.setLocality("SP");
        wareHouseEccomerce.setQuantity(2);
        wareHouseEccomerce.setType("ECOMMERCE");

        WarehouseDTO wareHousePhysicalStore = new WarehouseDTO();
        wareHousePhysicalStore.setLocality("SP");
        wareHousePhysicalStore.setQuantity(1);
        wareHousePhysicalStore.setType("PHYSICAL_STORE");

        List<WarehouseDTO> warehouses = new ArrayList<>();
        warehouses.add(wareHouseEccomerce);
        warehouses.add(wareHousePhysicalStore);

        InventoryDTO inventory = new InventoryDTO();
        inventory.setWarehouses(warehouses);

        ProductDTO product = new ProductDTO();
        product.setSku(1564);
        product.setName("L'oreal");
        product.setInventory(inventory);

        return product;
    }
}
