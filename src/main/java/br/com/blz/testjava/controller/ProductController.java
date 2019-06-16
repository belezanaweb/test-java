package br.com.blz.testjava.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;

/**
 * Class responsible for receiving requests from REST clients.
 * @author Andre Barroso
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {

	/**
	 * Product service.
	 */
	@Autowired
	private ProductService service;
	
	/**
	 * Method responsible for creating new products.
	 * 
	 * @param product New product.
	 * @return Product entity base.
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product createProduct(@Valid @RequestBody(required = true) Product product) {
		
		return this.service.createProduct(product);
	}
	
	/**
	 * Method responsible for updating products.
	 * 
	 * @param product Product to update.
	 * @return Result.
	 */
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean updateProduct(@Valid @RequestBody(required = true) Product product) {
		
		return this.service.updateProduct(product);
	}
	
	/**
	 * Method responsible for searching for a product through sku.
	 * 
	 * @param sku Product sku.
	 * @return Entity base.
	 */
	@GetMapping("/{sku}")
	public Product findProduct(@PathVariable(name = "sku", required = true) Long sku) {
		
		return this.service.findProduct(sku);
	}
	
	/**
	 * Method responsible for deleting a product through sku.
	 * 
	 * @param sku Product sku.
	 * @return Boolean true or false.
	 */
	@DeleteMapping("/{sku}")
	public boolean deleteProduct(@PathVariable(name = "sku", required = true) Long sku) {
		
		return this.service.removeProduct(sku);
	}
	
}
