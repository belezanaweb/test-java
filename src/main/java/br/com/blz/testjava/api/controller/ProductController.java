package br.com.blz.testjava.api.controller;

import java.util.List;

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

import br.com.blz.testjava.application.ProductService;
import br.com.blz.testjava.domain.model.Product;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product> findAll(){
		return productService.findAll();
	}
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Valid @RequestBody Product product) {
        return productService.save(product);
    }
	
	@PutMapping("/{sku}")
	public Product update(@PathVariable Long sku, 
			@Valid @RequestBody Product product) {
		product.setSku(sku);
		return productService.save(product);
	}
	
	@DeleteMapping("/{sku}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long sku) {
		productService.remove(sku);
	}
}
