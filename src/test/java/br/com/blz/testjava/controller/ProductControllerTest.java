package br.com.blz.testjava.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.com.blz.testjava.service.ProductService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService productService;
	
	@DisplayName("Test, list all products.")
	@Test
	public void listAllProducts() throws Exception {
		when(productService.getAll()).thenReturn(null);
		this.mockMvc.perform(get("/product")).andExpect(status().isOk());		
	}
	
	@DisplayName("Test, error list all products.")
	@Test
	public void createNewProduct() throws Exception {
		when(productService.getAll()).thenReturn(null);
		this.mockMvc.perform(post("/products")).andExpect(status().isNotFound());
	}
}
