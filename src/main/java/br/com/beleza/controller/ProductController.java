package br.com.beleza.controller;

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

import br.com.beleza.model.Product;
import br.com.beleza.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody Product product) {
		this.productService.save(product);
	}
	
	@GetMapping(value = "/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product find(@PathVariable("sku") Integer sku) {
		return this.productService.find(sku);
	}
	
	@DeleteMapping("/{sku}")
	public void delete(@PathVariable("sku") Integer sku) {
		productService.delete(new Product(sku));
	}
	
	@PutMapping(value = "/{sku}"
			, consumes = MediaType.APPLICATION_JSON_VALUE
			, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product edit(@PathVariable("sku") Integer sku, @RequestBody Product product) {
		this.productService.update(product);
		return product;
	}
}
