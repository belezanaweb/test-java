package br.com.blz.testjava.controller;




import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
	
	 protected MockMvc mockMvc;
	 
	 @InjectMocks
	 private ProductController productControllerMock;
	 
	 @Mock
	 private ProductService productServiceMock;
	 
	 
	 @Mock
	 private Product product;
	 
	 @Before
	 public void init() {
	      this.mockMvc = MockMvcBuilders.standaloneSetup(productControllerMock).build();	
	 }
	 
	 @Test
	 public void insertProductTest() throws Exception {
		 mockMvc.perform(
	                post("/product")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(getPayloadRequest()))
	                .andExpect(content().string("Produto criado com sucesso!"))
	                .andExpect(status().isOk());
	  }
	 
	 @Test
	 public void updateProductTest() throws Exception {
		 mockMvc.perform(
	                put("/product")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(getPayloadRequest()))
	                .andExpect(content().string("Produto atualizado com sucesso!"))
	                .andExpect(status().isOk());
	  }
	 
	 
	  @Test
	  public void deleteNotExistsProductTest() throws Exception {
	       	        
		  this.mockMvc.perform(MockMvcRequestBuilders.delete("/product/sku/99999")).andExpect(MockMvcResultMatchers.status().isNotFound());

				 
	  }
	 
	  @Test
	  public void deleteNONexistentProductTest() throws Exception {
	        mockMvc.perform(
	                delete("/product/sku/{sku}", 43269L)
	                        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(content().string("Produto não encontrado!"))
	                .andExpect(status().isNotFound());
	  }
	  
	  @Test
	  public void findProductBySkuNotFoundTest() throws Exception{
		  		 
	       this.mockMvc.perform(MockMvcRequestBuilders.get("/product/sku/99999")).andExpect(MockMvcResultMatchers.status().isNotFound());

	  }
 
	  private String getPayloadRequest() {
	        return "{\n" +
	                "    \"sku\": 43264,\n" +
	                "    \"name\": \"L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g\",\n" +
	                "    \"inventory\": {\n" +
	                "        \"quantity\": 15,\n" +
	                "        \"warehouses\": [\n" +
	                "            {\n" +
	                "                \"locality\": \"SP\",\n" +
	                "                \"quantity\": 12,\n" +
	                "                \"type\": \"ECOMMERCE\"\n" +
	                "            },\n" +
	                "            {\n" +
	                "                \"locality\": \"MOEMA\",\n" +
	                "                \"quantity\": 3,\n" +
	                "                \"type\": \"PHYSICAL_STORE\"\n" +
	                "            }\n" +
	                "        ]\n" +
	                "    },\n" +
	                "    \"isMarketable\": true\n" +
	                "}";
	  }
}
