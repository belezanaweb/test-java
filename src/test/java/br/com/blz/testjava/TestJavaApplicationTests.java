package br.com.blz.testjava;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.blz.testjava.retail.controller.foundation.MerchandiseController;
import br.com.blz.testjava.retail.model.foundation.Item;

@WebMvcTest(MerchandiseController.class)
@TestMethodOrder(OrderAnnotation.class)
public class TestJavaApplicationTests {


	@Autowired
	private MockMvc mockMvc;

	@Test	
	@Order(1) 
	public void contextLoads() throws Exception {	
	}

	@Test
	@Order(2) 
	public void createItem() throws Exception {
		
		Item newItem = new Item ("123456", "Chanel Allure", "CURITIBA", 5);

			ResultActions resultActions = mockMvc.perform( MockMvcRequestBuilders
			.post("/merchandise/createItem")
			.content(asJsonString(newItem))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$.sku").exists())
			
			;
			
			resultActions.andDo(MockMvcResultHandlers.print());

	}


	@Test
	@Order(3) 
	public void updateItem() throws Exception 
	{
		Item newItem = new Item ("123456", "Alure", "LONDRINA", 5);
		

	
		ResultActions resultActions = mockMvc.perform( MockMvcRequestBuilders
		  .put("/merchandise/updateItem")
		  .content(asJsonString(newItem))
		  .contentType(MediaType.APPLICATION_JSON)
		  .accept(MediaType.APPLICATION_JSON))
		  .andExpect(status().isOk())		  
		  ;

		  resultActions.andDo(MockMvcResultHandlers.print());

	}


	@Test
	@Order(4) 
	public void getAllItems() throws Exception 
	{
		ResultActions resultActions = mockMvc.perform( MockMvcRequestBuilders
		.get("/merchandise/retrieveAllItems")
		.accept(MediaType.APPLICATION_JSON))				
		.andExpect(status().isOk())		
		;
		resultActions.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(5)
	public void deleteItem() throws Exception 
	{
		ResultActions resultActions = mockMvc.perform( MockMvcRequestBuilders.delete("/merchandise/deleteItem/{SKU}", 123456) )
			.andExpect(status().isAccepted());

		resultActions.andDo(MockMvcResultHandlers.print());			
	}
 
	
	@Disabled
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
