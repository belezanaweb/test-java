package br.com.blz.testjava.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dtos.CreateProductRequest;
import br.com.blz.testjava.dtos.EditProductRequest;
import br.com.blz.testjava.dtos.ProductResponse;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired ProductService service;
	
	@GetMapping("/{sku}")
	@ResponseStatus(HttpStatus.OK)
	public ProductResponse getProduct(@PathVariable Long sku) {
		return service.getProduct(sku);
	}
	
	@DeleteMapping("/{sku}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@PathVariable Long sku) {
		service.deleteProduct(sku);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponse createProduct(@Valid @RequestBody CreateProductRequest product) {
		return service.createProduct(product);
	}

	@PutMapping("/{sku}")
	@ResponseStatus(HttpStatus.OK)
	public ProductResponse editProduct(@PathVariable("sku") Long sku, @RequestBody EditProductRequest product) {
		return service.editProduct(sku,product);
	}

}
