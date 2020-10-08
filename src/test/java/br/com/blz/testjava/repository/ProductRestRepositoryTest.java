package br.com.blz.testjava.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.ProductMock;

@DataMongoTest
class ProductRestRepositoryTest {
	
	@Autowired
	private ProductRestRepository productRepository;
	
	@Test
	void shouldBeEqual_whenSaveProduct() {
		Product product = ProductMock.getNewProduct();
		Product savedProduct = productRepository.save(product);
		
		assertEquals(product, savedProduct);
	}
	
	@Test
	void shouldBeEqual_whenFindASavedProduct() {
		Product product = ProductMock.getNewProduct();
		productRepository.save(product);
		
		Optional<Product> productRecovered = productRepository.findById(product.getSku());
		assertTrue(productRecovered.isPresent());
		assertEquals(product, productRecovered.orElseGet(null));
	}
	
	@Test
	void shouldBeEqual_whenFindAMergedProduct() {
		Product product = ProductMock.getNewProduct();
		productRepository.save(product);
		
		Product productUpdated = ProductMock.getNewProductThatHasStockInOnlyOneofTwoWarehouses();
		productUpdated.setSku(product.getSku());
		productRepository.save(productUpdated);
		
		Optional<Product> productRecovered = productRepository.findById(product.getSku());
		assertTrue(productRecovered.isPresent());
		assertEquals(productUpdated, productRecovered.orElseGet(null));
	}
	
	@Test
	void shouldBeNotFind_whenFindADeletedProduct() {
		Product product = ProductMock.getNewProduct();
		productRepository.save(product);
		productRepository.deleteById(product.getSku());
		
		Optional<Product> productRecovered = productRepository.findById(product.getSku());
		assertFalse(productRecovered.isPresent());
	}
	
}
