package br.com.blz.testjava.business;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.ProductMock;

@SpringBootTest
@AutoConfigureMockMvc
class BusinessRulesTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void shouldReturnQuantity15_whenRecoverAProductWith10and5InWareHouses() throws Exception {
		Product product = ProductMock.getNewProductThatHasStockInTwoWarehouses();
		mockMvc.perform(post("/product").content(asJsonString(product))).andExpect(status().isCreated());
		MvcResult result = mockMvc.perform(get("/product/{sku}", product.getSku())).andExpect(status().isOk()).andReturn();
		
		assertTrue(result.getResponse().getContentAsString().contains("\"quantity\" : 15"));
	}
	
	@Test
	void shouldReturnMarketableTrue_whenRecoverAProductWithStock() throws Exception {
		Product product = ProductMock.getNewProductThatHasStockInTwoWarehouses();
		mockMvc.perform(post("/product").content(asJsonString(product))).andExpect(status().isCreated());
		MvcResult result = mockMvc.perform(get("/product/{sku}", product.getSku())).andExpect(status().isOk()).andReturn();
		
		assertTrue(result.getResponse().getContentAsString().contains("\"marketable\" : true"));
	}
	
	@Test
	void shouldReturnBadRequest_whenPostTwoProductsWithSameSku() throws Exception {
		Product product = ProductMock.getNewProductThatHasStockInTwoWarehouses();
		mockMvc.perform(post("/product").content(asJsonString(product))).andExpect(status().isCreated());
		mockMvc.perform(post("/product").content(asJsonString(product))).andExpect(status().isBadRequest()).andReturn();
	}
	
	@Test
	void shouldReturnUpdatedProduct_whenPutAProductsChanged() throws Exception {
		//Save product with no warehouses
		Product product = ProductMock.getNewProduct();
		mockMvc.perform(post("/product").content(asJsonString(product))).andExpect(status().isCreated());
		MvcResult resultGet = mockMvc.perform(get("/product/{sku}", product.getSku())).andExpect(status().isOk()).andReturn();

		//Save product with 2 warehouses and same sku
		Product product2 = ProductMock.getNewProductThatHasStockInTwoWarehouses();
		product2.setSku(product.getSku());
		mockMvc.perform(put("/product/{sku}", product2.getSku()).content(asJsonString(product2))).andExpect(status().isNoContent());
		MvcResult resultGet2 = mockMvc.perform(get("/product/{sku}", product.getSku())).andExpect(status().isOk()).andReturn();
		
		assertNotEquals(resultGet.getResponse().getContentAsString(), (resultGet2.getResponse().getContentAsString()));
	}
	
	private String asJsonString(Product object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Fail to convert object to String");
		}
	}
}