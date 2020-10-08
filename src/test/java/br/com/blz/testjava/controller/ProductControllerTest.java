package br.com.blz.testjava.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.ProductMock;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void shouldReturnOk_whenGetList() throws Exception {
		mockMvc.perform(get("/product")).andExpect(status().isOk());
	}
	
	@Test
	void shouldReturnOk_whenGetListById() throws Exception {
		Product product = ProductMock.getNewProduct();
		mockMvc.perform(post("/product").content(asJsonString(product))).andExpect(status().isCreated());
		mockMvc.perform(get("/product/{sku}", product.getSku())).andExpect(status().isOk());
	}
	
	@Test
	void shouldReturnCreated_whenPost() throws Exception {
		mockMvc.perform(post("/product").content(asJsonString(ProductMock.getNewProduct()))).andExpect(status().isCreated());
	}
	
	@Test
	void shouldReturnNoContent_whenPut() throws Exception {
		Product product = ProductMock.getNewProduct();
		mockMvc.perform(put("/product/{sku}", product.getSku()).content(asJsonString(product))).andExpect(status().isCreated());
		mockMvc.perform(put("/product/{sku}", product.getSku()).content(asJsonString(product))).andExpect(status().isNoContent());
	}
	
	@Test
	void shouldReturnNoContent_whenPatch() throws Exception {
		Product product = ProductMock.getNewProduct();
		mockMvc.perform(patch("/product/{sku}", product.getSku()).content(asJsonString(product))).andExpect(status().isNotFound());
		mockMvc.perform(post("/product").content(asJsonString(product))).andExpect(status().isCreated());
		mockMvc.perform(patch("/product/{sku}", product.getSku()).content(asJsonString(product))).andExpect(status().isNoContent());
	}
	
	@Test
	void shouldReturnNoContent_whenDelete() throws Exception {
		Product product = ProductMock.getNewProduct();
		mockMvc.perform(post("/product").content(asJsonString(product))).andExpect(status().isCreated());
		mockMvc.perform(delete("/product/{sku}", product.getSku())).andExpect(status().isNoContent());
	}
	
	@Test
	void shouldThrow_whenPostProductWithoutId() throws Exception {
		Product product = ProductMock.getNewProductWithoutId();
		mockMvc.perform(post("/product").content(asJsonString(product))).andExpect(status().isBadRequest());
	}
	
	private String asJsonString(Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Fail to convert object to String");
		}
	}
}