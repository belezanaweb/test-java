package br.com.blz.testjava.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.DuplicatedEntityException;
import br.com.blz.testjava.mock.MockObject;
import br.com.blz.testjava.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	
	
	@Autowired
    private MockMvc mvc;
	 
	@MockBean
	private ProductService productService;
	
	
	@Test
	public void testSaveSuccess() throws Exception{
		Mockito.when(productService.save(Mockito.any(Product.class))).thenReturn(MockObject.getProduct());
		mvc.perform(post("/product")
				  .content(new ObjectMapper().writeValueAsString(MockObject.getProduct()))
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
		
	}
	
	
	@Test
	public void testSaveDuplicatedException() throws Exception{
		Mockito.when(productService.save(Mockito.any(Product.class))).thenThrow(new DuplicatedEntityException());
		mvc.perform(post("/product")
				  .content(new ObjectMapper().writeValueAsString(MockObject.getProduct()))
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isUnprocessableEntity());
		
	}
	
	@Test
	public void testSaveNotNull() throws Exception{
		mvc.perform(post("/product")
				  .content(new ObjectMapper().writeValueAsString(MockObject.getProductWithoutName()))
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isUnprocessableEntity());
	}
	

}
