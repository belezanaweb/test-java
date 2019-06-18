package br.com.blz.testjava.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.models.Inventory;
import br.com.blz.testjava.models.Warehouse;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InventoryControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    private static final ObjectMapper mapper=new ObjectMapper();
    
    
    
    
       @Test
		public void addNewInventoryTest() throws Exception{	 		    
		    List<Warehouse> warehouses = new LinkedList<Warehouse>();
		    warehouses.add(new Warehouse("SP",15,"ECOMERCE"));
		    warehouses.add(new Warehouse("CE",15,"STORE"));
		    Inventory inventory =  new Inventory(warehouses);
		          
		    //Convert obj to JSON format
		    String json = mapper.writeValueAsString(inventory);
		    mockMvc.perform(post("/inventory/add")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isOk());	    		   
		}
       
       
                    
   	@Test
   	public void updateInventoryTest() throws Exception{
   		Long sku =1l;
   	    List<Warehouse> warehouses = new LinkedList<Warehouse>();
   	    warehouses.add(new Warehouse("SP",215,"ECOMERCE"));
   	    warehouses.add(new Warehouse("CE",125,"STORE"));
   	    warehouses.add(new Warehouse("CE",355,"STORE"));
   	    Inventory inventory =  new Inventory(warehouses);
   	    
   	    
   	    //Convert obj to JSON format
   	    String json = mapper.writeValueAsString(inventory);
   	    mockMvc.perform(
   	    	MockMvcRequestBuilders.put("/inventory/update/"+sku)
   	       .contentType(MediaType.APPLICATION_JSON)
   	       .content(json)
   	       .accept(MediaType.APPLICATION_JSON))	    
   	       .andExpect(status().isOk())
   	       .andExpect(MockMvcResultMatchers.content().string("Updated"))
   	       .andDo(print());   	          	  
   	}
   	
	@Test
	public void getAllInventoriesTest() throws Exception{
		this.mockMvc.perform(get("/inventory/all"))
		.andDo(print())
		.andExpect(status().isOk())		
		;
		   
	}
	
}
