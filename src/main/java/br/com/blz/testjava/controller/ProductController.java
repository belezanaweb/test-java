package br.com.blz.testjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("/products/{sku}")
	public ResponseEntity<Product> findProductBy(@PathVariable("sku") int sku){
		return service.findProductBy(sku);
	}
	
	@PutMapping("/products/{sku}")
	public Product updateProductBy(@PathVariable("sku") int sku, @RequestBody Product product){
		return service.updateProductBy(sku, product);
	}
	
	@DeleteMapping("/products/{sku}")
	public void deleteProductBy(@PathVariable("sku") int sku){
		service.deleteProductBy(sku);
	}
	
	@PostMapping("/products")
	public ResponseEntity<Object> addProductBy(@RequestBody Product product){
		return service.addProduct(product);
	}
}
