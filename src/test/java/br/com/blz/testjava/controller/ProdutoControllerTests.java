package br.com.blz.testjava.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.blz.testjava.TestJavaApplicationTests;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.service.impl.ProdutoServiceImpl;

public class ProdutoControllerTests extends TestJavaApplicationTests {
 
	protected MockMvc mvc;
	
	@Autowired
	private ProdutoController produtoController;
	
	@Mock
	private ProdutoServiceImpl serviceImpl;
	
	@Mock
	private Produto produto;
	
	@Before
	public void setUp() throws Exception {		
		 this.mvc = MockMvcBuilders.standaloneSetup(produtoController).build();				 
	}
		
	@Test
	public void crudProduto() throws Exception  {	
		
		Produto produto = new Produto();
		
		produto.setName("sabonete");
		produto.setSku(new Long(1));
		
		Inventory inventory = new Inventory();		
		List<Warehouse> listWarehouse = new ArrayList<Warehouse>();
		
		Warehouse warehouse = new Warehouse();
		warehouse.setLocality("MOEMA");
		warehouse.setType("PHYSICAL_STORE");
		warehouse.setQuantity(new Long(5));
		listWarehouse.add(warehouse);
		
		inventory.setWarehouses(listWarehouse);		
		produto.setInventory(inventory);		
		
		String json = super.mapToJson(produto);		
		
		// addProduto
		MvcResult mvcResult = this.mvc.perform(MockMvcRequestBuilders.post("/produto")
																	 .contentType(MediaType.APPLICATION_JSON_VALUE)						
																	 .content(json)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	
		mvcResult = this.mvc.perform(MockMvcRequestBuilders.put("/produto")
				 			.contentType(MediaType.APPLICATION_JSON_VALUE)						
				 			.content(json)).andReturn();

		status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		this.mvc.perform(MockMvcRequestBuilders.get("/produto?sku=1")).andExpect(MockMvcResultMatchers.status().isOk());
		
		this.mvc.perform(MockMvcRequestBuilders.delete("/produto?sku=1")).andExpect(MockMvcResultMatchers.status().isOk());
			
		
	}
	
}
