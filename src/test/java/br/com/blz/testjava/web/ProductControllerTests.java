package br.com.blz.testjava.web;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void listaDeProdutosVaziaDeveRetornarAutoReferencia() throws Exception {
		
		this.mockMvc.perform(get("/products"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("{\"_links\":{\"self\":{\"href\":\"http://localhost/products\"}}}")));
	}

	@Test
	public void postSalvaProdutoCorretamente() throws Exception {
		
		this.mockMvc.perform(post("/products")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\n"
					+ "    \"sku\": 43264,\n"
					+ "    \"name\": \"mascara de reconstrucao 500g\",\n"
					+ "    \"inventory\": {\n"
					+ "        \"warehouses\": [\n"
					+ "            {\n"
					+ "                \"locality\": \"SP\",\n"
					+ "                \"quantity\": 12,\n"
					+ "                \"type\": \"ECOMMERCE\"\n"
					+ "            }\n"
					+ "        ]\n"
					+ "    }\n"
					+ "}") 
	           .accept(MediaType.APPLICATION_JSON))
	           .andExpect(status().isCreated())
	           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	           .andExpect(header().string("Location", "http://localhost/products/43264"))
	           .andExpect(jsonPath("$.name").value("mascara de reconstrucao 500g")) 
	           .andExpect(jsonPath("$.inventory.warehouses[0].locality").value("SP"))
	           .andExpect(jsonPath("$.inventory.warehouses[0].quantity").value("12"))
	           .andExpect(jsonPath("$.inventory.warehouses[0].type").value("ECOMMERCE"));
		
		this.mockMvc.perform(delete("/products/43264"));
		
	}
	
	
	
	
	@Test
	public void postAtualizaProdutoCorretamente() throws Exception {
		
		this.mockMvc.perform(post("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n"
						+ "    \"sku\": 43264,\n"
						+ "    \"name\": \"mascara de reconstrucao 500g\",\n"
						+ "    \"inventory\": {\n"
						+ "        \"warehouses\": [\n"
						+ "            {\n"
						+ "                \"locality\": \"SP\",\n"
						+ "                \"quantity\": 12,\n"
						+ "                \"type\": \"ECOMMERCE\"\n"
						+ "            }\n"
						+ "        ]\n"
						+ "    }\n"
						+ "}"));
		
		this.mockMvc.perform(put("/products/43264")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\n"
					+ "    \"sku\": 43264,\n"
					+ "    \"name\": \"mascara de reconstrucao 600g\",\n"
					+ "    \"inventory\": {\n"
					+ "        \"warehouses\": [\n"
					+ "            {\n"
					+ "                \"locality\": \"SP\",\n"
					+ "                \"quantity\": 12,\n"
					+ "                \"type\": \"ECOMMERCE\"\n"
					+ "            },\n"
					+ "            {\n"
					+ "                \"locality\": \"MOEMA\",\n"
					+ "                \"quantity\": 3,\n"
					+ "                \"type\": \"PHYSICAL_STORE\"\n"
					+ "            }\n"
					+ "        ]\n"
					+ "    }\n"
					+ "}") 
	           .accept(MediaType.APPLICATION_JSON))
	           .andExpect(status().isCreated())
	           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	           .andExpect(header().string("Location", "http://localhost/products/43264"))
	           .andExpect(jsonPath("$.name").value("mascara de reconstrucao 600g")) 
	           .andExpect(jsonPath("$..locality", hasItem("SP")))
	           .andExpect(jsonPath("$..quantity", hasItem(12)))
	           .andExpect(jsonPath("$..type", hasItem("ECOMMERCE")))
	           .andExpect(jsonPath("$..locality", hasItem("MOEMA")))
	           .andExpect(jsonPath("$..quantity", hasItem(3)))
	           .andExpect(jsonPath("$..type", hasItem("PHYSICAL_STORE")));
		
		this.mockMvc.perform(delete("/products/43264"));

	}
	
	@Test
	public void getRetornaProdutoComPropriedadesEsperadas() throws Exception {
		
		this.mockMvc.perform(post("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n"
						+ "    \"sku\": 43264,\n"
						+ "    \"name\": \"mascara de reconstrucao 600g\",\n"
						+ "    \"inventory\": {\n"
						+ "        \"warehouses\": [\n"
						+ "            {\n"
						+ "                \"locality\": \"SP\",\n"
						+ "                \"quantity\": 12,\n"
						+ "                \"type\": \"ECOMMERCE\"\n"
						+ "            },\n"
						+ "            {\n"
						+ "                \"locality\": \"MOEMA\",\n"
						+ "                \"quantity\": 3,\n"
						+ "                \"type\": \"PHYSICAL_STORE\"\n"
						+ "            }\n"
						+ "        ]\n"
						+ "    }\n"
						+ "}"));
		
		this.mockMvc.perform(get("/products/43264")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
	           .andExpect(status().isOk())
	           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	           .andExpect(jsonPath("$.name").value("mascara de reconstrucao 600g")) 
	           .andExpect(jsonPath("$..locality", hasItem("SP")))
	           .andExpect(jsonPath("$..quantity", hasItem(12)))
	           .andExpect(jsonPath("$..type", hasItem("ECOMMERCE")))
	           .andExpect(jsonPath("$..locality", hasItem("MOEMA")))
	           .andExpect(jsonPath("$..quantity", hasItem(3)))
	           .andExpect(jsonPath("$..type", hasItem("PHYSICAL_STORE")))
	           .andExpect(jsonPath("$.isMarketable").value("true")) 
	           .andExpect(jsonPath("$.inventory.quantity").value(15));
		
		this.mockMvc.perform(delete("/products/43264"));

	}
	
	
	@Test
	public void testaIsMarketableZerado() throws Exception {
		
		this.mockMvc.perform(post("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n"
						+ "    \"sku\": 43264,\n"
						+ "    \"name\": \"mascara de reconstrucao 600g\",\n"
						+ "    \"inventory\": {\n"
						+ "        \"warehouses\": [\n"
						+ "            {\n"
						+ "                \"locality\": \"SP\",\n"
						+ "                \"quantity\": 0,\n"
						+ "                \"type\": \"ECOMMERCE\"\n"
						+ "            }\n"
						+ "        ]\n"
						+ "    }\n"
						+ "}") 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isCreated())
		           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		           .andExpect(header().string("Location", "http://localhost/products/43264"))
		           .andExpect(jsonPath("$.inventory.warehouses[0].quantity").value(0))
		           .andExpect(jsonPath("$.isMarketable").value("false")) 
		           .andExpect(jsonPath("$.inventory.quantity").value(0));
		
		this.mockMvc.perform(delete("/products/43264"));

	}
	
	@Test
	public void testaErroAoCriarProdutoJaExistente() throws Exception {
		
		this.mockMvc.perform(post("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n"
						+ "    \"sku\": 43264,\n"
						+ "    \"name\": \"mascara de reconstrucao 500g\",\n"
						+ "    \"inventory\": {\n"
						+ "        \"warehouses\": [\n"
						+ "            {\n"
						+ "                \"locality\": \"SP\",\n"
						+ "                \"quantity\": 12,\n"
						+ "                \"type\": \"ECOMMERCE\"\n"
						+ "            }\n"
						+ "        ]\n"
						+ "    }\n"
						+ "}") 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isCreated());
		
		this.mockMvc.perform(post("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n"
						+ "    \"sku\": 43264,\n"
						+ "    \"name\": \"mascara de reconstrucao 500g\",\n"
						+ "    \"inventory\": {\n"
						+ "        \"warehouses\": [\n"
						+ "            {\n"
						+ "                \"locality\": \"SP\",\n"
						+ "                \"quantity\": 12,\n"
						+ "                \"type\": \"ECOMMERCE\"\n"
						+ "            }\n"
						+ "        ]\n"
						+ "    }\n"
						+ "}") 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isForbidden())
		           .andExpect(content().string("Product 43264 already exists"));
	}
	
	
	@Test
	public void testaApagarProduto() throws Exception {
		
		this.mockMvc.perform(post("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n"
						+ "    \"sku\": 43264,\n"
						+ "    \"name\": \"mascara de reconstrucao 500g\",\n"
						+ "    \"inventory\": {\n"
						+ "        \"warehouses\": [\n"
						+ "            {\n"
						+ "                \"locality\": \"SP\",\n"
						+ "                \"quantity\": 12,\n"
						+ "                \"type\": \"ECOMMERCE\"\n"
						+ "            }\n"
						+ "        ]\n"
						+ "    }\n"
						+ "}") 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isCreated());
		
		this.mockMvc.perform(delete("/products/43264")
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isNoContent());
		
		this.mockMvc.perform(get("/products/43264")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isNotFound())
		           .andExpect(content().string("Could not find product 43264"));
		
		
	}
	
	

	
}
