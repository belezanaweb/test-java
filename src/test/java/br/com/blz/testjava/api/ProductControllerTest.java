package br.com.blz.testjava.api;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.api.exceptions.ErrorEntity;
import br.com.blz.testjava.model.InventoryItem;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.dto.ProductDTO;
import br.com.blz.testjava.model.enums.StockType;
import br.com.blz.testjava.model.view.ProductViews.ProductRead;
import br.com.blz.testjava.model.view.ProductViews.ProductWrite;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductService service;

	@Autowired
	private ProductRepository repository;

	private static final String URL_BASE = "/products";

	private static final String SKU_DEFAULT = "SKU-0001";
	
	private static final String SKU_DEFAULT_ERROR = "SKU-0526";

	@Test
	@Order(1)
	void testPostSuccess() {
		ProductDTO dto = this.service.entityToDTO(this.getProductDefault());
		try {
			String json = this.objectMapper.writerWithView(ProductWrite.class).writeValueAsString(dto);
			mockMvc.perform(post(URL_BASE)
					.contentType(MediaType.APPLICATION_JSON)
					.content(json))
					.andExpect(status().isOk())
					.andDo(print());
			
			assertTrue(this.repository.existsById(SKU_DEFAULT));
		} catch (JsonProcessingException e) {
			log.error("fail on serialize object {}", e);
		} catch (Exception e) {
			log.error("Error in post test", e);
		}
	}
	
	@Test
	void testPostError() {
		ProductDTO dto = this.service.entityToDTO(this.getProductDefault());
		try {
			String json = this.objectMapper.writerWithView(ProductWrite.class).writeValueAsString(dto);
			MvcResult r = mockMvc.perform(post(URL_BASE)
					.contentType(MediaType.APPLICATION_JSON)
					.content(json))
					.andExpect(status().isBadRequest())
					.andDo(print()).andReturn();
			
			ErrorEntity error = this.objectMapper.readValue(r.getResponse().getContentAsString(), ErrorEntity.class);
			
			assertEquals("ProductExists", error.getCode());
			
		} catch (JsonProcessingException e) {
			log.error("fail on serialize object {}", e);
		} catch (Exception e) {
			log.error("Error in post test", e);
		}
	}
	
	@Test
	void testUpdateSuccess() {
		ProductDTO dto = this.service.entityToDTO(this.getProductDefault());
		
		dto.getInventory().getItems().get(0).setQuantity(40L);
		try {
			String json = this.objectMapper.writerWithView(ProductWrite.class).writeValueAsString(dto);
			MvcResult r = mockMvc.perform(put(URL_BASE+"/{idProduct}", SKU_DEFAULT)
					.contentType(MediaType.APPLICATION_JSON)
					.content(json))
					.andExpect(status().isOk())
					.andDo(print()).andReturn();
			
			ProductDTO product = this.objectMapper
					.readerWithView(ProductRead.class)
					.forType(ProductDTO.class)
					.readValue(r.getResponse().getContentAsString());

			assertEquals(product.getInventory().getQuantity(), 340L);
		} catch (JsonProcessingException e) {
			log.error("fail on serialize object {}", e);
		} catch (Exception e) {
			log.error("Error in post test", e);
		}
	}
	
	@Test
	void testUpdateError() {
		ProductDTO dto = this.service.entityToDTO(this.getProductDefault());
		try {
			String json = this.objectMapper.writerWithView(ProductWrite.class).writeValueAsString(dto);
			MvcResult r = mockMvc.perform(put(URL_BASE+"/{idProduct}", SKU_DEFAULT_ERROR)
					.contentType(MediaType.APPLICATION_JSON)
					.content(json))
					.andExpect(status().isNotFound())
					.andDo(print()).andReturn();
			
			ErrorEntity error = this.objectMapper
					.readValue(r.getResponse().getContentAsString(), ErrorEntity.class);
			
			assertEquals("ProductNotExists", error.getCode());
			
		} catch (JsonProcessingException e) {
			log.error("fail on serialize object {}", e);
		} catch (Exception e) {
			log.error("Error in post test", e);
		}
	}
	
	@Test
	void testGetSuccess() {
	
		try {
			mockMvc.perform(get(URL_BASE+"/{idProduct}", SKU_DEFAULT)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("\"isMarketable\":true")))
					.andDo(print());
		
		} catch (Exception e) {
			log.error("Error in post test", e);
		}
	}
	
	@Test
	void testGetError() {
		try {
			MvcResult r = mockMvc.perform(get(URL_BASE+"/{idProduct}", SKU_DEFAULT_ERROR)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isNotFound())
					.andDo(print())
					.andReturn();
			
			ErrorEntity error = this.objectMapper
					.readValue(r.getResponse().getContentAsString(), ErrorEntity.class);
			
			assertEquals("ProductNotExists", error.getCode());
		
		} catch (Exception e) {
			log.error("Error in post test", e);
		}
	}
	
	@Test
	void testDeleteSuccess() {
	
		try {
			mockMvc.perform(delete(URL_BASE+"/{idProduct}", SKU_DEFAULT)
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isNoContent())
					.andDo(print());
		
		} catch (Exception e) {
			log.error("Error in post test", e);
		}
	}
	
	@Test
	void testDeleteError() {
		try {
			MvcResult r = mockMvc.perform(delete(URL_BASE+"/{idProduct}", SKU_DEFAULT_ERROR)
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isNotFound())
					.andDo(print())
					.andReturn();
			
			ErrorEntity error = this.objectMapper
					.readValue(r.getResponse().getContentAsString(), ErrorEntity.class);
			
			assertEquals("ProductNotExists", error.getCode());
		
		} catch (Exception e) {
			log.error("Error in post test", e);
		}
	}
	
	private Product getProductDefault() {
		Product product = new Product();
		product.setSKU(SKU_DEFAULT);
		product.setName("first product");

		List<InventoryItem> items = new ArrayList<>();

		InventoryItem item = new InventoryItem();

		item.setLocality("sp");
		item.setQuantity(10L);
		item.setType(StockType.PHYSICAL_STORE);
		items.add(item);

		item = new InventoryItem();
		item.setLocality("RJ");
		;
		item.setQuantity(300L);
		item.setType(StockType.ECOMMERCE);
		items.add(item);

		product.setInventory(items);

		return product;

	}

}
