package br.com.blz.produtos.apirest.resources;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.hamcrest.Matchers.*;

import br.com.blz.produtos.apirest.repository.RepositoryProduto;
import br.com.blz.produtos.apirest.repository.RepositoryWarehouse;

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(HelloController.class)
@RunWith(SpringRunner.class) 
@WebMvcTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ResourceProdutoTest {
	
	@MockBean
	RepositoryProduto repositoryProduto;
	
	@MockBean
	RepositoryWarehouse repositoryWarehouse;
	
	@Autowired
	ResourceProduto resourceProduto;
	
	@Autowired
	ResourceWarehouse resourceWarehouse;
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	void verify_Get_produtoEmpty_200() throws Exception {
	
		mvc.perform(MockMvcRequestBuilders.get("/api/produto/1"))
		//.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().json("{}"))
		;
	}
	
	
	@Test
	void verify_Post_produto_201() throws Exception {
		
		String produto = "{\"sku\": 1, \"name\": \"Produto 01\"}";
		
		mvc.perform(MockMvcRequestBuilders.post("/api/produto")
					.content(produto)
					.contentType(MediaType.APPLICATION_JSON)
			    	)
		//.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isCreated())
		;
	}
	
	@Test
	void verify_Post_produtoNullName_400() throws Exception {
		
		String produto = "{\"sku\": 1}";
		
		mvc.perform(MockMvcRequestBuilders.post("/api/produto")
					.content(produto)
					.contentType(MediaType.APPLICATION_JSON)
			    	)
		//.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Campo nulo: name"))
		;
	}

	@Test
	void verify_Post_produtoNotNull_400() throws Exception {
		
		String produto = "{\"sku\": 2}";
		
		mvc.perform(MockMvcRequestBuilders.post("/api/produto")
					.content(produto)
					.contentType(MediaType.APPLICATION_JSON)
			    	)
		//.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Campo nulo: name"))
		;
	}
	
}
