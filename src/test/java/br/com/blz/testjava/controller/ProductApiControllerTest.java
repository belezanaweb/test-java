/**
 * 
 */
package br.com.blz.testjava.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.WareHouseDTO;
import br.com.blz.testjava.service.ProductService;
import junit.framework.Assert;

/**
 * @author Augusto Lemes
 * @since 16/06/2019
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductApiController.class, secure = false)
public class ProductApiControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService productService;
	
	@Test
	public void testCreateProduct() throws Exception {
		WareHouseDTO mockWareHouse = new WareHouseDTO("SP",1, "ECOMMERCE");
		List<WareHouseDTO> listaMock = new ArrayList<WareHouseDTO>();
		listaMock.add(mockWareHouse);
		InventoryDTO mockInventory = new InventoryDTO(listaMock);
		
		ProductDTO mockProduct = new ProductDTO(1L, "teste", mockInventory);
		
		String productJson = mapToJson(mockProduct);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/save")
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(productJson)).andReturn();

		System.out.println(result.getResponse());
		Integer expected = 200;
		Integer statusResult = result.getResponse().getStatus();

		
		Assert.assertEquals(expected, statusResult);
	}
	
	@Test
	public void testFindOne() throws Exception {

		WareHouseDTO mockWareHouse = new WareHouseDTO("SP",1, "ECOMMERCE");
		List<WareHouseDTO> listaMock = new ArrayList<WareHouseDTO>();
		listaMock.add(mockWareHouse);
		InventoryDTO mockInventory = new InventoryDTO(listaMock);
		
		ProductDTO mockProduct = new ProductDTO(1L, "teste", mockInventory);
		
		String productJson = mapToJson(mockProduct);
		
		Mockito.when(
				productService.findOne(1L)).thenReturn(mockProduct);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/find/1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = productJson;

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void testDelete() throws Exception {

		WareHouseDTO mockWareHouse = new WareHouseDTO("SP",1, "ECOMMERCE");
		List<WareHouseDTO> listaMock = new ArrayList<WareHouseDTO>();
		listaMock.add(mockWareHouse);
		InventoryDTO mockInventory = new InventoryDTO(listaMock);
		
		ProductDTO mockProduct = new ProductDTO(1L, "teste", mockInventory);
		
		String productJson = mapToJson(mockProduct);
		
		Mockito.when(
				productService.delete(1L)).thenReturn(Boolean.TRUE);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/find/1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		Integer expected = 200;
		Integer statusResult = result.getResponse().getStatus();

		
		Assert.assertEquals(expected, statusResult);
	}

	   private String mapToJson(Object obj) throws JsonProcessingException {
		      ObjectMapper objectMapper = new ObjectMapper();
		      return objectMapper.writeValueAsString(obj);
		   }
	
}
