package br.com.blz.testjava;

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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestJavaApplicationTests {
	
	
	@Autowired
	private MockMvc mvc;

	 
	@Test
	public void createEmployeeAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders
	      .post("/v1/products/insert")
	      .content("{\n" + 
	      		"    \"sku\": 43264,\n" + 
	      		"    \"name\": \"L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g\",\n" + 
	      		"    \"inventory\": {\n" + 
	      		"        \"quantity\": 0,\n" + 
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
	      		"    \"isMarketable\": false\n" + 
	      		"}")
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(MockMvcResultMatchers.status().isOk());
	}

	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
}
