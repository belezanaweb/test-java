package br.com.blz.testjava.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.blz.testjava.TestJavaApplication;
import br.com.blz.testjava.dto.ProductDTO;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(classes = {TestJavaApplication.class})
public class ProductRepositoryTest {

	@Autowired
	ProductRepository repository;
	
	@After
	public void initRepository() {
		repository.list = new HashMap<Integer, ProductDTO>();
	}
	
	@Test
	public void testGetProductWhenHasProduct() {
		ProductDTO product = new ProductDTO();
		product.setSku(1);
		repository.list.put(1, product);
		assertNotNull(repository.getProduct(1));
	}
	
	@Test
	public void testGetProductWhenHasntProduct() {
		assertNull(repository.getProduct(2));
	}

	@Test
	public void testDeleteProductExists() {
		ProductDTO product = new ProductDTO();
		product.setSku(1);
		repository.list.put(1, product);
		assertNotNull(repository.deleteProduct(1));
	}
	
	@Test
	public void testDeleteProductNotExists() {
		assertNull(repository.deleteProduct(2));
	}
	
	@Test
	public void testCreateProduct() throws Exception {
		ProductDTO product = new ProductDTO();
		product.setSku(1);
		repository.createProduct(product);
		assertTrue(repository.list.containsKey(product.getSku()));
	}
	
	@Test(expected=Exception.class)
	public void testCreateProductTwice() throws Exception {
		ProductDTO product = new ProductDTO();
		product.setSku(1);
		repository.createProduct(product);
		repository.createProduct(product);
	}

	@Test
	public void testUpdateProductExists() {
		ProductDTO product = new ProductDTO();
		product.setSku(1);
		product.setName("Blz na Web");
		repository.list.put(1, product);
		ProductDTO product2 = new ProductDTO();
		product2.setSku(1);
		product2.setName("Beleza Na Web");
		repository.updateProduct(product2);
		assertEquals(repository.list.get(product2.getSku()).getName(), product2.getName());
	}
	
	@Test
	public void testUpdateProductNotExists() {
		ProductDTO product = new ProductDTO();
		product.setSku(1);
		ProductDTO pd = repository.updateProduct(product);
		assertNull(pd);
	}

}
