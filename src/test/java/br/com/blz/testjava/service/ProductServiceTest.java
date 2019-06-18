package br.com.blz.testjava.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.DuplicatedEntityException;
import br.com.blz.testjava.mock.MockObject;
import br.com.blz.testjava.repository.ProductRepositoryImpl;


@RunWith(SpringRunner.class)
public class ProductServiceTest {

	
	 @TestConfiguration
	 static class ProductServiceTestContextConfiguration {
	    @Bean
	    public ProductService productService() {
	         return new ProductServiceImpl();
	    }
	 }
	 
	 
	 @Autowired
	 private ProductService productService;
	 
	 @MockBean
	 private ProductRepositoryImpl productRepository;
	 
	 
	 @Test
	 public void testSaveSuccess() throws DuplicatedEntityException{
		 Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(MockObject.getProduct());
		 Product product = productService.save(MockObject.getProduct());
		 assertNotNull(product);
		 assertNotNull(product.getName());
		 
	 }
	 
	 @Test(expected=DuplicatedEntityException.class)
	 public void testSaveError() throws DuplicatedEntityException{
		 Mockito.when(productRepository.save(Mockito.any(Product.class))).thenThrow(new DuplicatedEntityException());
		 productService.save(MockObject.getProduct());
		 
	 }
	 
}
