package br.com.blz.testjava.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.exception.ProductSkuException;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

	private static final Logger logger = LogManager.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@PostMapping("/products")
	public ResponseEntity<String> add(@RequestBody Product newProduct) {
		logger.info("Adding new product : " + newProduct);

		if(newProduct.getSku() == null)
			throw new ProductSkuException("Invalid sku: " + newProduct.getSku());
		
		Optional<Product> product = productService.findBySku(newProduct.getSku());

		if (product.isPresent())
			throw new ProductSkuException("There is other product already created with the same sku.");
		
		productService.save(newProduct);
		return new ResponseEntity<String>("Product created successfully", HttpStatus.CREATED);
	}

	@PutMapping("/products/{sku}")
	public ResponseEntity<String> update(@RequestBody Product product, @PathVariable Long sku) {
		logger.info("Updating product : " + product);

		productService.update(product, sku);

		return new ResponseEntity<String>("Product updated successfully", HttpStatus.CREATED);
	}

	@GetMapping("products/{sku}")
	public ResponseEntity<Optional<Product>> findBySku(@PathVariable Long sku) {
		logger.info("Searching product by sku: {}", sku);
		System.out.print(productService.findBySku(sku));
		return new ResponseEntity<Optional<Product>>(productService.findBySku(sku), HttpStatus.OK);
	}

	@DeleteMapping("products/{sku}")
	public ResponseEntity<String> delete(@PathVariable Long sku) {
		logger.info("Removing product {} ", sku);

		Optional<Product> product = productService.findBySku(sku);

		if (!product.isPresent())
			throw new ProductNotFoundException("Product does not exist in database.");

		productService.delete(product.get());
		return new ResponseEntity<String>("Product removed successfully", HttpStatus.OK);
	}
}
