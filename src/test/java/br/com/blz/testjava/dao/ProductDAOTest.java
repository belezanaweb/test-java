package br.com.blz.testjava.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ProductDAOTest {
	
	private static final String PRODUCT_NAME = "Sabonete Dove";
	private Product product;
	
	@InjectMocks
	ProductDAO dao;
	
	@Mock
	ProductRepository repo;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		Product newProduct = new Product();
		newProduct.setSku(1L);
		newProduct.setName(PRODUCT_NAME);
		product = newProduct;
	}
	
	@Test
	public void insert() {
		Mockito.when(repo.insert(any() ) ).thenReturn(product);
		
		Product insert = dao.insert(new Product());
		assertEquals(product, insert);
	}
	
	@Test
	public void update() {
		Mockito.when(repo.update(any() ) ).thenReturn(product);
		
		Product insert = dao.update(new Product());
		assertEquals(product, insert);
	}
	
	@Test
	public void delete() {
		Mockito.when(repo.delete(any() ) ).thenReturn(product);
		
		Product insert = dao.delete(1L);
		assertEquals(product, insert);
	}
	
	@Test
	public void selectBySku() {
		Mockito.when(repo.get(any() ) ).thenReturn(product);
		
		Product insert = dao.selectBySku(1L);
		assertEquals(product, insert);
	}
}
