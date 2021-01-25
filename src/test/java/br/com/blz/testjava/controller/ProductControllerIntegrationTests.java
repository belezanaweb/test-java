package br.com.blz.testjava.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.WareHouseDTO;
import br.com.blz.testjava.type.WareHouseType;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductControllerIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	

	@Test
	public void createTest() throws JsonProcessingException, Exception {
		
		ProductDTO prodTeste = new ProductDTO();
		prodTeste.setSku(1L);
		prodTeste.setName("Nome do produto 1");

		InventoryDTO invTeste = new InventoryDTO();
		WareHouseDTO wrTeste = new WareHouseDTO();
		wrTeste.setLocality("PR");
		wrTeste.setQuantity(1);
		wrTeste.setType(WareHouseType.ECOMMERCE);

		WareHouseDTO wrTeste2 = new WareHouseDTO();
		wrTeste2.setLocality("SP");
		wrTeste2.setQuantity(3);
		wrTeste2.setType(WareHouseType.PHYSICAL_STORE);
		invTeste.getWarehouses().add(wrTeste);
		invTeste.getWarehouses().add(wrTeste2);
		prodTeste.setInventory(invTeste);
		 
		mockMvc.perform(
				post("/product").contentType("application/json").content(objectMapper.writeValueAsString(prodTeste)))
				.andExpect(status().isCreated());

	}

	@Test
	public void createTestDuplicado() throws JsonProcessingException, Exception {

		ProductDTO prodTeste = new ProductDTO();
		prodTeste.setSku(1L);
		prodTeste.setName("Nome do produto 1");

		InventoryDTO invTeste = new InventoryDTO();
		WareHouseDTO wrTeste = new WareHouseDTO();
		wrTeste.setLocality("PR");
		wrTeste.setQuantity(1);
		wrTeste.setType(WareHouseType.ECOMMERCE);

		WareHouseDTO wrTeste2 = new WareHouseDTO();
		wrTeste2.setLocality("SP");
		wrTeste2.setQuantity(3);
		wrTeste2.setType(WareHouseType.PHYSICAL_STORE);
		invTeste.getWarehouses().add(wrTeste);
		invTeste.getWarehouses().add(wrTeste2);
		prodTeste.setInventory(invTeste);
		
		
		mockMvc.perform(
				post("/product").contentType("application/json").content(objectMapper.writeValueAsString(prodTeste)))
				.andExpect(status().isCreated());

		mockMvc.perform(
				post("/product").contentType("application/json").content(objectMapper.writeValueAsString(prodTeste)))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void deleteTest() throws JsonProcessingException, Exception {
		ProductDTO prodTeste = new ProductDTO();
		prodTeste.setSku(1L);
		prodTeste.setName("Nome do produto 1");
		
		InventoryDTO invTeste = new InventoryDTO();
		WareHouseDTO wrTeste = new WareHouseDTO();
		wrTeste.setLocality("PR");
		wrTeste.setQuantity(1);
		wrTeste.setType(WareHouseType.ECOMMERCE);
		
		WareHouseDTO wrTeste2 = new WareHouseDTO();
		wrTeste2.setLocality("SP");
		wrTeste2.setQuantity(3);
		wrTeste2.setType(WareHouseType.PHYSICAL_STORE);
		invTeste.getWarehouses().add(wrTeste);
		invTeste.getWarehouses().add(wrTeste2);
		prodTeste.setInventory(invTeste);
		
		mockMvc.perform(
				post("/product").contentType("application/json").content(objectMapper.writeValueAsString(prodTeste)))
				.andExpect(status().isCreated());
		
		mockMvc.perform(
				delete("/product/1").contentType("application/json").content(objectMapper.writeValueAsString(prodTeste)))
		.andExpect(status().isOk());
		
		
	}
	
	@Test
	public void getTest() throws JsonProcessingException, Exception {
		ProductDTO prodTeste = new ProductDTO();
		prodTeste.setSku(1L);
		prodTeste.setName("Nome do produto 1");
		
		InventoryDTO invTeste = new InventoryDTO();
		WareHouseDTO wrTeste = new WareHouseDTO();
		wrTeste.setLocality("PR");
		wrTeste.setQuantity(1);
		wrTeste.setType(WareHouseType.ECOMMERCE);
		
		WareHouseDTO wrTeste2 = new WareHouseDTO();
		wrTeste2.setLocality("SP");
		wrTeste2.setQuantity(3);
		wrTeste2.setType(WareHouseType.PHYSICAL_STORE);
		invTeste.getWarehouses().add(wrTeste);
		invTeste.getWarehouses().add(wrTeste2);
		prodTeste.setInventory(invTeste);
		
		mockMvc.perform(
				post("/product").contentType("application/json").content(objectMapper.writeValueAsString(prodTeste)))
				.andExpect(status().isCreated());
		
		mockMvc.perform(
				get("/product/1").contentType("application/json"))
		.andExpect(status().isOk());
		
		mockMvc.perform(
				get("/product/2").contentType("application/json"))
		.andExpect(status().isNotFound());
		
		
	}
	
	
	@Test
	public void getTestNotFound() throws JsonProcessingException, Exception {
		
		mockMvc.perform(
				get("/product/2").contentType("application/json"))
		.andExpect(status().isNotFound());
		
		
	}
	
	
	
	@Test
	public void updateTest() throws JsonProcessingException, Exception {
		ProductDTO prodTeste = new ProductDTO();
		prodTeste.setSku(1L);
		prodTeste.setName("Nome do produto 1");
		
		InventoryDTO invTeste = new InventoryDTO();
		WareHouseDTO wrTeste = new WareHouseDTO();
		wrTeste.setLocality("PR");
		wrTeste.setQuantity(1);
		wrTeste.setType(WareHouseType.ECOMMERCE);
		
		WareHouseDTO wrTeste2 = new WareHouseDTO();
		wrTeste2.setLocality("SP");
		wrTeste2.setQuantity(3);
		wrTeste2.setType(WareHouseType.PHYSICAL_STORE);
		invTeste.getWarehouses().add(wrTeste);
		invTeste.getWarehouses().add(wrTeste2);
		prodTeste.setInventory(invTeste);
		
		mockMvc.perform(
				post("/product").contentType("application/json").content(objectMapper.writeValueAsString(prodTeste)))
				.andExpect(status().isCreated());
		
		
		prodTeste.setSku(1L);
		prodTeste.setName("Nome do produto 3");
		
		
		mockMvc.perform(
				put("/product/1").contentType("application/json").content(objectMapper.writeValueAsString(prodTeste)))
		.andExpect(status().isOk());
		
	}
	
}
