package br.com.blz.testjava.controller;

import javax.validation.Valid;

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

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("/{sku}")
	public ResponseEntity<Product> findProductBy(@PathVariable("sku") Long sku){
		Product found = service.findProductBy(sku);
		return ResponseEntity.status(HttpStatus.OK).body(found);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Product>> findAll(){
		Iterable<Product> found = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(found);
	}
	
	@PutMapping("/{sku}")
	public ResponseEntity<Product> updateProductBy(@PathVariable("sku") Long sku, @RequestBody @Valid Product product){
		Product updated = service.updateProductBy(sku, product);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}
	
	@DeleteMapping("/{sku}")
	public ResponseEntity<Object> deleteProductBy(@PathVariable("sku") Long sku){
		service.deleteProductBy(sku);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PostMapping
	public ResponseEntity<Object> addProductBy(@RequestBody @Valid Product product){
		Product added = service.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(added);
	}
}
