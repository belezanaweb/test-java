package br.com.blz.testjava.controllers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
import br.com.blz.testjava.models.Product;
import br.com.blz.testjava.models.Warehouse;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

	

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper mapper=new ObjectMapper();
    

   @Rule
   public ExpectedException exception = ExpectedException.none();
    
    
	@Test
	public void addProducTest() throws Exception{
	    
	    List<Warehouse> warehouses = new LinkedList<Warehouse>();
	    warehouses.add(new Warehouse("SP",15,"ECOMERCE"));
	    warehouses.add(new Warehouse("CE",15,"STORE"));
	    Inventory inventory =  new Inventory(warehouses);
	    
	    Product product = new Product(  "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", inventory);
	    
	    //Convert obj to JSON format
	    String json = mapper.writeValueAsString(product);
	    mockMvc.perform(
	    	post("/product/add")
	       .contentType(MediaType.APPLICATION_JSON)
	       .content(json)
	       .accept(MediaType.APPLICATION_JSON))	    
	       .andExpect(status().isOk())
	       .andExpect(MockMvcResultMatchers.content().string("Saved"))
	       .andDo(print());	       
	   
	}
	
	
	@Test
	public void updateProducTest() throws Exception{
		Long sku =1l;
	    List<Warehouse> warehouses = new LinkedList<Warehouse>();
	    warehouses.add(new Warehouse("SP",215,"ECOMERCE"));
	    warehouses.add(new Warehouse("CE",125,"STORE"));
	    warehouses.add(new Warehouse("CE",355,"STORE"));
	    Inventory inventory =  new Inventory(warehouses);
	    
	    Product product = new Product(  sku, "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", inventory);
	    
	    //Convert obj to JSON format
	    String json = mapper.writeValueAsString(product);
	    mockMvc.perform(
	    	MockMvcRequestBuilders.put("/product/update/"+sku)
	       .contentType(MediaType.APPLICATION_JSON)
	       .content(json)
	       .accept(MediaType.APPLICATION_JSON))	    
	       .andExpect(status().isOk())
	       .andExpect(MockMvcResultMatchers.content().string("Updated"))
	       .andDo(print());      	   
	}

	
	// @Test
	    public void productAlreadyExistsErrorTest() throws Exception {
		 Long sku =1l;
		 //Testing exception 
		 exception.expect(Exception.class);
		 exception.expectMessage("There already exists a product with this sku "+sku);
		 
		 List<Warehouse> warehouses = new LinkedList<Warehouse>();
		    warehouses.add(new Warehouse("SP",15,"ECOMERCE"));
		    warehouses.add(new Warehouse("CE",15,"STORE"));
		    
		    Inventory inventory =  new Inventory(warehouses);
		    
		    Product product = new Product( sku, "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", inventory);
		    
		    //Convert obj to JSON format
		    String json = mapper.writeValueAsString(product);
		    mockMvc.perform(
		    	post("/product/add")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))	    
		       .andExpect(status().isInternalServerError())
		       .andDo(print());
		       ; 
	    }	 
	
	@Test
	public void getAllProductTest() throws Exception{
		Long sku=1l;
		this.mockMvc.perform(get("/product/"+sku))
		.andDo(print())
		.andExpect(status().isOk())		
		;			   
	}	
	
	@Test
	public void getAllProductsTest() throws Exception{
		this.mockMvc.perform(get("/product/all"))
		.andDo(print())
		.andExpect(status().isOk())		
		;
	}
		
	//@Test
	public void deleteProductTest() throws Exception{
		this.mockMvc.perform(delete("/product/65"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("Deleted"))
		;
	}

	
}
