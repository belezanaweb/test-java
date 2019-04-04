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
import org.springframework.http.ResponseEntity;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;

@SuppressWarnings({ "rawtypes"})
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
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
		Mockito.when(service.postProduct(any() ) ).thenReturn(product);
		
		ResponseEntity testProduct = controller.postProduct(new Product());
		assertEquals(product, testProduct.getBody());
	}
	
	@Test
	public void update() {
		Mockito.when(service.updateProductWithForm(any() ) ).thenReturn(product);
		
		ResponseEntity testProduct = controller.updateProductWithForm(new Product());
		assertEquals(product, testProduct.getBody());
	}
	
	@Test
	public void delete() {
		Mockito.when(service.deleteProduct(any() ) ).thenReturn(product);
		
		ResponseEntity testProduct = controller.deleteProduct(1L);
		assertEquals(product, testProduct.getBody());
	}
	
	@Test
	public void getProductBySKU() {
		Mockito.when(service.getProductBySKU(any() ) ).thenReturn(product);
		
		ResponseEntity testProduct = controller.getProductBySKU(1L);
		assertEquals(product, testProduct.getBody());
	}
}
