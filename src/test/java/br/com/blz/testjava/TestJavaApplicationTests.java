package br.com.blz.testjava;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.models.Inventory;
import br.com.blz.testjava.models.Produto;
import br.com.blz.testjava.models.WareHouse;

@SpringBootTest
@AutoConfigureMockMvc
public class TestJavaApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/teste").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Greetings from Spring Boot!")));
	}
	
	@Test
	public void getAllProdutosAPI() throws Exception 
	{
	  mvc.perform( MockMvcRequestBuilders
	      .get("/api/produtos")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.[*].sku").isNotEmpty());
	}
	 
	@Test
	public void getProdutosByIdAPI() throws Exception 
	{
	  mvc.perform( MockMvcRequestBuilders
	      .get("/api/produto/{id}", 1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.sku").value(1));
	}
	
	@Test
	public void createProdutoAPI() throws Exception 
	{
	  mvc.perform( MockMvcRequestBuilders
	      .post("/api/produto")
	      .content(asJsonString(createProduto(2)))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isCreated())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.sku").exists());
	}
	
	private Produto createProduto(long id) {
		WareHouse a1 = new WareHouse("SP",12,"ECOMMERCE");
		WareHouse a2 = new WareHouse("MOEMA",13,"PHYSICAL_STORE");
		Inventory inv = new Inventory();
		inv.addWarehouse(a1);
		inv.addWarehouse(a2);
		
		Produto p = new Produto();
		p.setSku(id);
		p.setInventory(inv);
		p.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
		
		return p;
	}
	 
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Test
	public void updateProdutoAPI() throws Exception 
	{
	  mvc.perform( MockMvcRequestBuilders
	      .put("/api/produto")
	      .content(asJsonString(createProduto(1)))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	  	  .andDo(print())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.quantity").value(25));
	}
	
	@Test
	public void deleteEmployeeAPI() throws Exception 
	{
	  mvc.perform( MockMvcRequestBuilders.delete("/api/produto/{id}", 1) );
	}
}

