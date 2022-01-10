package br.com.blz.testjava.controller;

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

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.DuplicateProductException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody final Product product){
		ResponseEntity<?> response = null;
		
		try {
			productService.save(product);
			
			response = new ResponseEntity<Product>(HttpStatus.CREATED);
		}catch (DuplicateProductException e) {
			response = new ResponseEntity<String>(String.format("Código sku: %s já cadastrado anteriormente", product.getSku()), HttpStatus.BAD_REQUEST);
		}

		return response;
	}
	
	@PutMapping("/{sku}")
	public ResponseEntity<?> updateBySku(@PathVariable final Integer sku, @RequestBody final Product product){
		ResponseEntity<?> response = null;
		
		try {
			productService.updateBySku(sku, product);
			
			response = new ResponseEntity<Product>(HttpStatus.OK);
		}catch (ProductNotFoundException e) {
			response = new ResponseEntity<String>(String.format("Produto sku: %s não encontrado", sku), HttpStatus.BAD_REQUEST);
		}

		return response;
	}
	
	@GetMapping("/{sku}")
	public ResponseEntity<?> findBySku(@PathVariable final Integer sku){
		ResponseEntity<?> response = null;
		
		try {
			final Product product = productService.findBySku(sku);
			
			response = new ResponseEntity<Product>(product, HttpStatus.OK);
		}catch (ProductNotFoundException e) {
			response = new ResponseEntity<String>(HttpStatus.OK);
		}

		return response;
	}
	
	@DeleteMapping("/{sku}")
	public ResponseEntity<?> deleteBySku(@PathVariable final Integer sku){
		ResponseEntity<?> response = null;
		
		try {
			productService.delete(sku);
			
			response = new ResponseEntity<Product>(HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			response = new ResponseEntity<String>(String.format("Produto sku: %s não encontrado", sku), HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}
}
