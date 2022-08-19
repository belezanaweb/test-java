package br.com.blz.testjava.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.equalToObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.WarehouseDTO;
import br.com.blz.testjava.entity.WarehouseEntity.WarehouseType;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	private ProductDTO productBase;
	private static final String BASE_URL = "/api/products";
	
	@BeforeEach
	void setup() throws Exception {
		this.productBase = ProductDTO.builder()
				.sku(Long.valueOf(123))
				.name("Produto 1")
				.inventory(InventoryDTO.builder()
						.addWarehouse(WarehouseDTO.builder()
								.locality("SP")
								.quantity(Long.valueOf(12))
								.type(WarehouseType.ECOMMERCE)
								.build()
								)
						.addWarehouse(WarehouseDTO.builder()
								.locality("PR")
								.quantity(Long.valueOf(3))
								.type(WarehouseType.PHYSICAL_STORE)
								.build()
								)
						.build())
				.build();
		
		//limpa o registro do teste anterior
		mockMvc.perform(delete(BASE_URL + "/"+ productBase.getSku()));
	}
	
	@Test
	void whenCreateWithSucess() throws Exception {
		mockMvc.perform(post(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.productBase))
				)
			.andDo(print())	
			.andExpect(status().isCreated());
	}
	
	@Test
	void whenCreateWithSameSKU() throws Exception {
		mockMvc.perform(post(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.productBase))
				)
			.andDo(print())	
			.andExpect(status().isCreated());
		
		mockMvc.perform(post(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.productBase))
				)
			.andDo(print())	
			.andExpect(status().isBadRequest());
	}
	
	@Test
	void whenUpdateWithSucess() throws Exception {

		mockMvc.perform(post(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.productBase))
				)
			.andDo(print())	
			.andExpect(status().isCreated());
		this.productBase.setName("Produto 2");
		mockMvc.perform(put(BASE_URL + "/" + this.productBase.getSku())
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.productBase))
				)
			.andDo(print())	
			.andExpect(status().isOk());
		
		mockMvc.perform(get(BASE_URL +"/" +this.productBase.getSku()))
			.andDo(print())	
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", equalTo("Produto 2")));	
	}
	
	@Test
	void whenUpdateWithBadRequest() throws Exception {
		mockMvc.perform(put(BASE_URL + "/12345")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.productBase))
				)
			.andDo(print())	
			.andExpect(status().isBadRequest());
		
	}
	
	@Test
	void whenUpdateWithNotFound() throws Exception {
		mockMvc.perform(put(BASE_URL + "/" + this.productBase.getSku())
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.productBase))
				)
			.andDo(print())	
			.andExpect(status().isNotFound());
		
		
	}
	
	@Test
	void whenTryCreateWithRequiredFieldNull() throws Exception {
		mockMvc.perform(post(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(ProductDTO.builder().build()))
				)
			.andDo(print())	
			.andExpect(status().isBadRequest());
	}
	
	@Test
	void whenDeleteWithSucess() throws Exception {
		mockMvc.perform(post(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.productBase))
				)
			.andDo(print())	
			.andExpect(status().isCreated());
		
		mockMvc.perform(delete(BASE_URL +"/" +this.productBase.getSku()))
			.andDo(print())	
			.andExpect(status().isOk());
		
	}
	
	@Test
	void whenDeleteWithNotFound() throws Exception {
		mockMvc.perform(delete(BASE_URL +"/" +this.
				productBase.getSku()))
			.andDo(print())	
			.andExpect(status().isNotFound());
	}
	
	@Test
	void whenGetWithSucess() throws Exception {
		mockMvc.perform(post(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.productBase))
				)
			.andDo(print())	
			.andExpect(status().isCreated());
		
		mockMvc.perform(get(BASE_URL +"/" +this.productBase.getSku()))
			.andDo(print())	
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.isMarketable", equalTo(true)))
			.andExpect(jsonPath("$.inventory.quantity", equalToObject(15)));		
	}
	
	@Test
	void whenGetWithNotFound() throws Exception {
		mockMvc.perform(get(BASE_URL + "/" +this.productBase.getSku()))
			.andDo(print())	
			.andExpect(status().isNotFound());		
	}
	
	@Test
	void whenGetAllWhithContent() throws Exception {
		mockMvc.perform(post(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.productBase))
				)
		.andDo(print())	
		.andExpect(status().isCreated());
		
		mockMvc.perform(get(BASE_URL))
			.andDo(print())	
			.andExpect(status().isOk());		
	}
	
	@Test
	void whenGetWithNoContent() throws Exception {
		mockMvc.perform(get(BASE_URL))
			.andDo(print())	
			.andExpect(status().isNoContent());		
	}
	
}
