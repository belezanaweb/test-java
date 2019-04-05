package br.com.blz.testjava.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.ResponseSuccess;
import br.com.blz.testjava.service.ProductService;

@SuppressWarnings({ "rawtypes"})
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
	private static final String SUCESS_PREFIX_MSG = "Product with id ";
	
	private Product product;
	
	@InjectMocks
	ProductController controller;
	
	@Mock
	ProductService service;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		Product newProduct = new Product();
		newProduct.setSku(1L);
		newProduct.setName("Sabonete Dove");
		product = newProduct;
	}
	
	@Test
	public void insert() {
		ResponseSuccess success = 
				new ResponseSuccess().message(
					SUCESS_PREFIX_MSG + product.getSku()+" was created");
		Mockito.when(service.postProduct(any() ) ).thenReturn(product);
		
		ResponseEntity testProduct = controller.createProduct(new Product());
		assertEquals(success, testProduct.getBody());
		assertEquals(HttpStatus.CREATED, testProduct.getStatusCode());
	}
	
	@Test
	public void update() {
		ResponseSuccess success = 
				new ResponseSuccess().message(
					SUCESS_PREFIX_MSG + product.getSku()+" was updated");
		Mockito.when(service.updateProduct(any() ) ).thenReturn(product);
		
		ResponseEntity testProduct = controller.updateProduct(new Product());
		assertEquals(success, testProduct.getBody());
		assertEquals(HttpStatus.OK, testProduct.getStatusCode());
	}
	
	@Test
	public void delete() {
		ResponseSuccess success = 
			new ResponseSuccess().message(
				SUCESS_PREFIX_MSG + product.getSku()+" was deleted"); 
		Mockito.when(service.deleteProduct(any() ) ).thenReturn(product);
		
		ResponseEntity testProduct = controller.deleteProduct(1L);
		assertEquals(success, testProduct.getBody());
		assertEquals(HttpStatus.OK, testProduct.getStatusCode());
	}
	
	@Test
	public void getProductBySKU() {
		Mockito.when(service.getProductBySKU(any() ) ).thenReturn(product);
		
		ResponseEntity testProduct = controller.getProductBySKU(1L);
		assertEquals(product, testProduct.getBody());
	}
}
