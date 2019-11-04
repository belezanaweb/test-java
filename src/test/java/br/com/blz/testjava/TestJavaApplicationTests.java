package br.com.blz.testjava;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestJavaApplication.class)
public class TestJavaApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	
	private ProductRepository repository = ProductRepository.getInstance();

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
			
		Product product = new Product();
		product.setSku(1L);
		product.setName("Product 1");
		
		List<Warehouse>list = new ArrayList<>();
		
		Warehouse wh1 = new Warehouse();
		wh1.setLocality("SP");
		wh1.setQuantity(10L);
		wh1.setType("ECOMMERCE");
		list.add(wh1);
		
		Inventory inventory = new Inventory();
		inventory.setWarehouse(list);
		product.setInventory(inventory);
		repository.save(product);
		
		Product product2 = new Product();
		product2.setSku(2L);
		product2.setName("Product 1");
		
		List<Warehouse>list2 = new ArrayList<>();
		
		Warehouse wh2 = new Warehouse();
		wh2.setLocality("MOEMA");
		wh2.setQuantity(20L);
		wh2.setType("PHYSICAL_STORE");
		list2.add(wh2);
		
		Inventory inventory2 = new Inventory();
		inventory2.setWarehouse(list2);
		product2.setInventory(inventory2);
		repository.save(product2);
		
		Product product3 = new Product();
		product3.setSku(3L);
		product3.setName("Product 3");
		repository.save(product3);
	}

	@Test
	public void testSaveProductWithInvalidSku() throws Exception {
		Product product = new Product();
		product.setSku(null);
		product.setName("Product Null");

		mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(product))).andExpect(status().isBadRequest())
				.andExpect(content().json(
						"{\"status\":400,\"detail\":\"Invalid sku: null\",\"developerMessage\":\"br.com.blz.testjava.exception.ProductSkuException\"}"));
	}

	@Test
	public void testSaveProductWithSkuDuplicated() throws Exception {
		Product product = new Product();
		product.setSku(1L);
		product.setName("Product 1");

		mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(product))).andExpect(status().isBadRequest())
				.andExpect(content().json(
						"{\"status\":400,\"detail\":\"There is other product already created with the same sku.\",\"developerMessage\":\"br.com.blz.testjava.exception.ProductSkuException\"}"));
	}

	@Test
	public void testSaveProductSuccess() throws Exception {
		Product product = new Product();
		product.setSku(4L);
		product.setName("Product 4");
		
		List<Warehouse>list = new ArrayList<>();
		
		Warehouse wh1 = new Warehouse();
		wh1.setLocality("SP");
		wh1.setQuantity(10L);
		wh1.setType("ECOMMERCE");
		list.add(wh1);
		
		Warehouse wh2 = new Warehouse();
		wh2.setLocality("MOEMA");
		wh2.setQuantity(5L);
		wh2.setType("PHYSICAL_STORE");
		list.add(wh2);
		
		Warehouse wh3 = new Warehouse();
		wh3.setLocality("SP");
		wh3.setQuantity(6L);
		wh3.setType("PHYSICAL_STORE");
		list.add(wh3);
		
		Inventory inventory = new Inventory();
		inventory.setWarehouse(list);
		product.setInventory(inventory);

		mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(product))).andExpect(status().isCreated())
				.andExpect(content().string("Product created successfully"));
	}
	
	@Test
	public void testUpdateProductSuccess() throws Exception {
		Product product = new Product();
		product.setSku(3L);
		product.setName("Product 3");
		
		List<Warehouse>list = new ArrayList<>();
		
		Warehouse wh1 = new Warehouse();
		wh1.setLocality("SP");
		wh1.setQuantity(10L);
		wh1.setType("ECOMMERCE");
		list.add(wh1);
		
		Warehouse wh2 = new Warehouse();
		wh2.setLocality("MOEMA");
		wh2.setQuantity(5L);
		wh2.setType("PHYSICAL_STORE");
		list.add(wh2);
		
		Warehouse wh3 = new Warehouse();
		wh3.setLocality("SP");
		wh3.setQuantity(6L);
		wh3.setType("PHYSICAL_STORE");
		list.add(wh3);
		
		Inventory inventory = new Inventory();
		inventory.setWarehouse(list);
		product.setInventory(inventory);

		mockMvc.perform(put("/api/v1/products/3").contentType(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(product))).andExpect(status().isCreated())
				.andExpect(content().string("Product updated successfully"));
	}
	
	
	@Test
    public void testFindProductBySku()throws Exception {
        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"sku\":1,\"name\":\"Product 1\",\"inventory\": {\"warehouses\":[{\"locality\":\"SP\",\"quantity\":10,\"type\":\"ECOMMERCE\"}], \"quantity\":10},\"marketable\":true}]"));
    }

    @Test
    public void testDeleteProductSuccess()throws Exception {
        mockMvc.perform(delete("/api/v1/products/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product removed successfully"));
    }

    @Test
    public void testDeleteProductNotFound()throws Exception {
        mockMvc.perform(delete("/api/v1/products/33"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"status\":404,\"detail\":\"Product does not exist in database.\",\"developerMessage\":\"br.com.blz.testjava.exception.ProductNotFoundException\"}"));
    }

	private String convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper Obj = new ObjectMapper();
		return Obj.writeValueAsString(object);
	}

}
