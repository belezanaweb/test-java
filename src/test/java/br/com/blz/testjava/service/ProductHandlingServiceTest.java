package br.com.blz.testjava.service;

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

import br.com.blz.testjava.dao.ProductDAO;
import br.com.blz.testjava.model.Product;

@RunWith(MockitoJUnitRunner.class)
public class ProductHandlingServiceTest {
	
	private Product product;
	
	@InjectMocks
	ProductHandlingService service;
	
	@Mock
	ProductDAO dao;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		Product newProduct = new Product();
		newProduct.setSku(1L);
		newProduct.setName("Sabonete Dove");
		product = newProduct;
	}
	
	@Test
	public void postProduct() {
		Mockito.when(dao.insert(any() ) ).thenReturn(product);
		
		Product insert = service.postProduct(new Product());
		assertEquals(product, insert);
	}
	
	@Test
	public void updateProduct() {
		Mockito.when(dao.update(any() ) ).thenReturn(product);
		
		Product insert = service.updateProduct(new Product());
		assertEquals(product, insert);
	}
	
	@Test
	public void deleteProduct() {
		Mockito.when(dao.delete(any() ) ).thenReturn(product);
		
		Product insert = service.deleteProduct(1L);
		assertEquals(product, insert);
	}
	
	@Test
	public void getProductBySKU() {
		Mockito.when(dao.selectBySku(any() ) ).thenReturn(product);
		
		Product insert = service.getProductBySKU(1L);
		assertEquals(product, insert);
	}
}
