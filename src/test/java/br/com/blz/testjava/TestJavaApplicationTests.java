package br.com.blz.testjava;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.TypeWarehouse;
import br.com.blz.testjava.model.Warehouse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestJavaApplicationTests {
	
	@Autowired
    private MockMvc mockMvc;


	@Test
	public void contextLoads() {
	}
	
	
	
	
	
	@Test
	public void testDeleteProduct() throws Exception {
		
		mockMvc.perform(delete("/products/delete/12346")).andExpect(status().isOk());
	}

	/**
	 * Realiza teste de inserção
	 * @throws Exception 
	 */
	@Test
	public void testSaveProduct() throws Exception {
		
		Product product = new Product();
		product.setSku(12346L);
		product.setName("Melancia");
		Inventory inventory = new Inventory();
		product.setInventory(inventory);
		Warehouse warehouse1 = new Warehouse();
		warehouse1.setLocality("SP");
		warehouse1.setQuantity(5);
		warehouse1.setType(TypeWarehouse.ECOMMERCE);
		
		Warehouse warehouse2 = new Warehouse();
		warehouse2.setLocality("BH");
		warehouse2.setQuantity(10);
		warehouse2.setType(TypeWarehouse.PHYSICAL_STORE);
		
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		inventory.setWarehouses(warehouses);
		mockMvc.perform(post("/products/").contentType(MediaType.APPLICATION_JSON).
				content(asJsonString(product))).andExpect(status().isOk());
		
		
	}

	@Test
	public void testAlterProduct() throws Exception {
		
		Product product = new Product();
		product.setName("Laranja");
		Inventory inventory = new Inventory();
		product.setInventory(inventory);
		Warehouse warehouse1 = new Warehouse();
		warehouse1.setLocality("RJ");
		warehouse1.setQuantity(5);
		warehouse1.setType(TypeWarehouse.ECOMMERCE);
		
		Warehouse warehouse2 = new Warehouse();
		warehouse2.setLocality("BH");
		warehouse2.setQuantity(10);
		warehouse2.setType(TypeWarehouse.PHYSICAL_STORE);
		
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		inventory.setWarehouses(warehouses);
		mockMvc.perform(put("/products/alter/12349").contentType(MediaType.APPLICATION_JSON).
				content(asJsonString(product))).andExpect(status().isOk());
		
		
	}
	
	@Test
	public void testGetProductById() throws Exception {
		
		mockMvc.perform(get("/products/12349")).andExpect(status().isOk()).andExpect(jsonPath("$.response.inventory.quantity").value(15));
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 
	

}
