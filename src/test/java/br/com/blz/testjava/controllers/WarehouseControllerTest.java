package br.com.blz.testjava.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
/*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
*/

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WarehouseControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void addWarehousesTest() throws Exception{
		
	//    String id = "1";
	    MockHttpServletRequestBuilder builderOK =
	            MockMvcRequestBuilders.post("/warehouse/add")
	                                  .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	                                  .accept(MediaType.APPLICATION_FORM_URLENCODED)
	                                  .characterEncoding("UTF-8")
	                                  .content("locality=SP&quantity=14&type=ECOMERCE");
	    this.mockMvc.perform(builderOK)
	    	.andExpect(MockMvcResultMatchers.status().isOk())
		    .andExpect(MockMvcResultMatchers.content().string("Saved"))
		    .andDo(MockMvcResultHandlers.print()) ;
			
    }
	
	//@Test
	public void updateWarehousesTest() throws Exception{
		
        String id = "1";
	    MockHttpServletRequestBuilder builderOK =
	            MockMvcRequestBuilders.put("/warehouse/update/"+id )
	                                  .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	                                  .accept(MediaType.APPLICATION_FORM_URLENCODED)
	                                  .characterEncoding("UTF-8")
	                                  .content("locality=SP&quantity=14&type=ECOMERCE UPDATED");
	    this.mockMvc.perform(builderOK)
	    	.andExpect(MockMvcResultMatchers.status().isOk())
		    .andExpect(MockMvcResultMatchers.content().string("Updated"))
		    .andDo(MockMvcResultHandlers.print()) ;
			
    }
	
	//@Test
	public void getWarehouseTest() throws Exception{
		Long id = 1l;
		this.mockMvc.perform(get("/warehouse/"+id))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().json("{'id':1,'locality':'SP','quantity':14,'type':'ECOMERCE UPDATED'}"))
		;
	}
	
		
	@Test
	public void getAllWarehousesTest() throws Exception{
		this.mockMvc.perform(get("/warehouse/all"))
		.andDo(print())
		.andExpect(status().isOk())		
		;
		   
	}
	
	//@Test
	public void deleteWarehouseTest() throws Exception{
		this.mockMvc.perform(delete("/warehouse/97"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("Deleted"))
		;
	}
	
	
	

}
