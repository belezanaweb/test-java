package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.ErrorResponse;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public ResponseEntity<Product> findProductBy(int sku) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.findBy(sku));
	}
	
	public ResponseEntity<Object> addProduct(Product product) {

		try {
			repository.addProduct(product);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorMessage(e.getMessage()));
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(product);

	}

	public ResponseEntity<Object> deleteProductBy(int sku) {
		boolean removed = repository.deleteProductBy(sku);
		
		if (removed)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}

	public ResponseEntity<Product> updateProductBy(int sku, Product product) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.updateProduct(sku, product));
	}
	
	private ErrorResponse getErrorMessage(String message) {
		return ErrorResponse.valueOf(message);
	}

}
