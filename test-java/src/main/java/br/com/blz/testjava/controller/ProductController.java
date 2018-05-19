package br.com.blz.testjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.exception.ProductAlreadyRegisteredException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer create(@RequestBody Product product) throws ProductAlreadyRegisteredException {
		return productService.saveProduct(product);
	}

	@PutMapping(path = "/{sku}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer update(@PathVariable Integer sku, @RequestBody Product product) throws ProductNotFoundException{
		return productService.updateProduct(sku, product);
	}
	
	@GetMapping(path = "/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product findProductBySku(@PathVariable Integer sku) throws ProductNotFoundException {
		return productService.findProductBySku(sku);
	}
	
	@DeleteMapping(path = "/{sku}")
	public Integer delete(@PathVariable Integer sku) throws ProductNotFoundException {
		return productService.deleteProduct(sku);
	}

}
