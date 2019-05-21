package br.com.blz.testjava;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.domain.Inventory;
import br.com.blz.domain.Product;
import br.com.blz.domain.Warehouse;
import br.com.blz.util.WarehouseType;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestJavaApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	/*
	@Autowired
    private MockMvc mvc;
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
		   
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(obj);
        return jsonContent;
	 
	}
	
	
	@Test
	public void testPostProduct () throws Exception {

		mvc.perform(MockMvcRequestBuilders.post("/product")
				  .content(asJsonString(getExample(43264)))
				  .contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}
	
	@Test
	public void testPutProduct () throws Exception {

		Product prod = getExample(43265);
		
		mvc.perform(MockMvcRequestBuilders.post("/product")
				  .content(asJsonString(prod))
				  .contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
		
		prod.getInventory().getWarehouses().get(0).setLocality("RJ");
		prod.getInventory().getWarehouses().get(0).setQuantity(14);

		mvc.perform(MockMvcRequestBuilders.put("/product")
				  .content(asJsonString(prod))
				  .contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void testGetProduct () throws Exception {

		mvc.perform(MockMvcRequestBuilders.post("/product")
				  .content(asJsonString(getExample(43266)))
				  .contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
		
		mvc.perform(get("/product/43266")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void testDeleteProduct () throws Exception {

		mvc.perform(MockMvcRequestBuilders.post("/product")
				  .content(asJsonString(getExample(43267)))
				  .contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
		
		mvc.perform(delete("/product/43267")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
	}
	
	private Product getExample(long sku){
		
		Warehouse w1 = new Warehouse();
		w1.setLocality("SP");
		w1.setQuantity(12);
		w1.setType(WarehouseType.ECOMMERCE);
		
		Warehouse w2 = new Warehouse();
		w2.setLocality("MOEMA");
		w2.setQuantity(3);
		w2.setType(WarehouseType.PHYSICAL_STORE);
		
		List<Warehouse> lsWarehouses = new ArrayList<Warehouse>();
		lsWarehouses.add(w1);
		lsWarehouses.add(w2);
		
		Inventory inventory = new Inventory();
		inventory.setWarehouses(lsWarehouses);
	
		Product prod = new Product();
		prod.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
		prod.setSku(new Long(sku));
		prod.setInventory(inventory);
		
		return prod;
	}*/
}
