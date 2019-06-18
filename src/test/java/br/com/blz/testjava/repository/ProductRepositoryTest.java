package br.com.blz.testjava.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.DuplicatedEntityException;
import br.com.blz.testjava.mock.MockObject;

@RunWith(SpringRunner.class)
public class ProductRepositoryTest {
	

	 @TestConfiguration
	 static class ProductRepositoryTestContextConfiguration {
	    @Bean
	    public ProductRepository productRepository() {
	         return new ProductRepositoryImpl();
	    }
	 }
	 
	 @Autowired
	 private ProductRepositoryImpl productRepositoryImpl;
	 
	 
	 @Test(expected=DuplicatedEntityException.class)
	 public void testSaveSuccess() throws DuplicatedEntityException{
		 Product product = MockObject.getProduct();
		 product = productRepositoryImpl.save(product);
		 assertNotNull(product);
		 assertNotNull(product.getName());
		 productRepositoryImpl.save(product);
		 
	 }
	 
	 @Test(expected=DuplicatedEntityException.class)
	 public void testSaveDuplicatedException() throws DuplicatedEntityException{
		 Product product = MockObject.getProduct();
		 product = productRepositoryImpl.save(product);
		productRepositoryImpl.save(product);
		 
	 }

}
