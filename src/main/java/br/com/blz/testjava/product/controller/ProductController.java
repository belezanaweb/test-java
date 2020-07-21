package br.com.blz.testjava.product.controller;

import javax.validation.Valid;

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

import br.com.blz.testjava.product.model.Product;
import br.com.blz.testjava.product.service.impl.ProductServiceImpl;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value="/product")
@AllArgsConstructor
public class ProductController {
	
	private ProductServiceImpl service;
	
	@PostMapping
	public ResponseEntity<HttpStatus> create(@RequestBody @Valid Product product) {
		service.create(product);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	@GetMapping("/{sku}")
	public ResponseEntity<Product>findOne(@PathVariable Long sku) {
		return new ResponseEntity<Product>(service.find(sku), HttpStatus.OK);
	}
	
	@DeleteMapping("/{sku}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Long sku) {
		service.delete(sku);
		return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{sku}")
	public ResponseEntity<Product> edit(@PathVariable Long sku, @RequestBody @Valid Product product) {
		return new ResponseEntity<Product>(service.update(sku, product), HttpStatus.OK);
	}
	
	

}
