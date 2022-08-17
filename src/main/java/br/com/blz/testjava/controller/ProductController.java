package br.com.blz.testjava.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("v1/product")

public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/{sku}")
	public ProductDTO retrieveProduct(final @PathVariable("sku") Integer sku) {
		return this.productService.retrieveProduct(sku);
	}

	@DeleteMapping(value = "/{sku}")
	public void deleteProduct(final @PathVariable("sku") Integer sku) {
		this.productService.deleteProduct(sku);
	}

	@Validated
	@PostMapping
	public void createProduct(final @Valid @RequestBody ProductDTO product) {
		productService.createProduct(product);
	}

	@PutMapping(value = "/{sku}")
	public void updateProduct(final @PathVariable("sku") Integer sku, final @RequestBody ProductDTO product) {
		productService.updateProduct(product);
	}

}
