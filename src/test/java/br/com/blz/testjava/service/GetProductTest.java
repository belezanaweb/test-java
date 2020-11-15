package br.com.blz.testjava.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
class GetProductTest {
	
	
	@Test
	void getNonExistentProductTest( @Mock ProductRepository repository) {
		Mockito.when(repository.findBySku(any(Long.class))).thenReturn(null);
		
		var service = new ProductService(repository);
		Throwable ex = assertThrows(ProductNotFoundException.class, ()->service.getProduct(12L));
		assertEquals("Produto não encontrado", ex.getMessage());
	}
	
	

}
