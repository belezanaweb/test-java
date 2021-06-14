package br.com.blz.testjava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.exception.ProductAlreadyExists;
import br.com.blz.testjava.exception.ProductNotFound;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/{sku}")
	public ResponseEntity<Product> getBySky(@PathVariable("sku") Long sku) {
		try {
			
			return ResponseEntity.ok(productService.getBySku(sku));
		
		} catch (ProductNotFound e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		try {
			return ResponseEntity.ok(productService.getAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		try {
		
			return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.create(product));
		
		} catch (ProductAlreadyExists e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/{sku}")
	public ResponseEntity<Product> update(@PathVariable("sku") Long sku, @RequestBody Product product) {
		try {
		
			return ResponseEntity.ok(this.productService.update(sku, product));

		} catch (ProductNotFound e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{sku}")
	public ResponseEntity<?> delete(@PathVariable("sku") Long sku) {
		try {
		
			this.productService.delete(sku);
			return ResponseEntity.ok().build();
		
		} catch (ProductNotFound e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
