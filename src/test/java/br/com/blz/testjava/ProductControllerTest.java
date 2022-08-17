package br.com.blz.testjava;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import br.com.blz.testjava.controller.ProductController;
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
        ProductDTO loreal = new ProductDTO();
        loreal.setSku(43264);
        loreal.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");

        InventoryDTO lorealInventory = new InventoryDTO();
        WarehouseDTO saoPaulo = new WarehouseDTO();
        WarehouseDTO moema = new WarehouseDTO();

        saoPaulo.setLocality("SP");
        saoPaulo.setType("ECOMMERCE");
        saoPaulo.setQuantity(12);

        moema.setLocality("MOEMA");
        moema.setType("PHYSICAL_STORE");
        moema.setQuantity(3);

        lorealInventory.setWarehouses(Arrays.asList(saoPaulo, moema));

        loreal.setInventory(lorealInventory);

        productController.createProduct(loreal);

        ProductDTO malbec = new ProductDTO();
        malbec.setSku(43265);
        malbec.setName("Malbec");

        InventoryDTO malbecInventory = new InventoryDTO();
        malbecInventory.setQuantity(10);
        WarehouseDTO curitiba = new WarehouseDTO();
        WarehouseDTO blumenau = new WarehouseDTO();

        curitiba.setLocality("Curitiba");
        curitiba.setType("ECOMMERCE");
        curitiba.setQuantity(6);

        blumenau.setLocality("Blumenau");
        blumenau.setType("ECOMMERCE");
        blumenau.setQuantity(2);

        malbecInventory.setWarehouses(Arrays.asList(curitiba, blumenau));

        malbec.setInventory(malbecInventory);

        productController.createProduct(malbec);
    }

    @Test
    public void testCreateProductWithBadRequest() throws Exception {
        JSONObject json = new JSONObject("{\"sk-XXX-u\":6,\"name\":\"perfume 6\",\"isMarketable\":true,\"inventory\":{\"quantity\":11,\"warehouses\":[{\"locality\":\"Rio de Janeiro\",\"quantity\":3,\"type\":\"ECOMMERCE\"},{\"locality\":\"São Paulo\",\"quantity\":8,\"type\":\"PHYSICAL_STORE\"}]}}");
        mockMvc.perform(post("/v1/product").contentType("application/json; charset=utf8").content(json.toString())).andExpect(status().isBadRequest());
    }

    @Test
    public void testRetrieveProductFound() throws Exception {
        mockMvc.perform(get("/v1/product/43264"))
        	.andExpect(status().isOk());
    }
    
    @Test
    public void testRetrieveProductNotFound() throws Exception {
        mockMvc.perform(get("/v1/product/43263"))
        	.andExpect(status().isNotFound());
    }
    
    @Test
    public void testQuantityProductsEquals8() throws Exception{
    	mockMvc.perform(get("/v1/product/43265"))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.inventory.quantity", is(8)));
    }
    
    @Test
    public void testIsMarketableIsTrue() throws Exception{
    	mockMvc.perform(get("/v1/product/43265"))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.isMarketable", is(true)));
    }

    @Test
    public void testCreateProduct() throws Exception {
    	JSONObject json = new JSONObject("{\"sku\":6,\"name\":\"Quasar\",\"isMarketable\":true,\"inventory\":{\"quantity\":11,\"warehouses\":[{\"locality\":\"Rio de Janeiro\",\"quantity\":3,\"type\":\"ECOMMERCE\"},{\"locality\":\"São Paulo\",\"quantity\":8,\"type\":\"PHYSICAL_STORE\"}]}}");
        mockMvc.perform(post("/v1/product").contentType("application/json; charset=utf8").content(json.toString()))
            .andExpect(status().isOk());
    }

    @Test
    public void testCreateProductAlreadyExisted() throws Exception{
    	JSONObject json = new JSONObject("{\"sku\":43265,\"name\":\"Egeo\",\"isMarketable\":true,\"inventory\":{\"quantity\":11,\"warehouses\":[{\"localilty\":\"Rio de Janeiro\",\"quantity\":3,\"type\":\"ECOMMERCE\"},{\"locality\":\"São Paulo\",\"quantity\":8,\"type\":\"PHYSICAL_STORE\"}]}}");
        mockMvc.perform(post("/v1/product").contentType("application/json; charset=utf8").content(json.toString()))
        	.andExpect(status().is(409));
    }

    @Test
    public void testUpdateProduct() throws Exception{
    	JSONObject json = new JSONObject("{\"sku\":43265,\"name\":\"Portinari\",\"isMarketable\":true,\"inventory\":{\"quantity\":11,\"warehouses\":[{\"localilty\":\"Rio de Janeiro\",\"quantity\":3,\"type\":\"ECOMMERCE\"},{\"locality\":\"São Paulo\",\"quantity\":8,\"type\":\"PHYSICAL_STORE\"}]}}");
    	mockMvc.perform(put("/v1/product/43265").contentType("application/json; charset=utf8").content(json.toString()))
    		.andExpect(status().isOk());
    }

    @Test
    public void testDeleteProduct() throws Exception {
    	mockMvc.perform(delete("/v1/product/1"))
    		.andExpect(status().isOk());
    }
}
