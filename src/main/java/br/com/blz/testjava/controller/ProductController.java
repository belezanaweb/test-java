package br.com.blz.testjava.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.blz.testjava.exception.DuplicatedEntityException;
import br.com.blz.testjava.exception.EntityNotFoundException;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired 
	private ProductService productService;
	
	
	@PostMapping
	public ResponseEntity<Product> save(@Valid @RequestBody Product product) throws DuplicatedEntityException{
		product = productService.save(product);
		return ResponseEntity.ok(product);
	}
	
	@PutMapping("/{sku}")
	public ResponseEntity<Product> update(@Valid @RequestBody Product product, @PathVariable(required=true) Long sku)throws EntityNotFoundException {
		product.setSku(sku);
		product = productService.update(product);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping("/{sku}")
	public ResponseEntity<Product> findBySku(@PathVariable(required=true)Long sku) throws EntityNotFoundException{
		return ResponseEntity.ok(productService.findBySku(sku));
	}
	
	@DeleteMapping("/{sku}")
	public ResponseEntity<Void> delete(@PathVariable(required=true)Long sku) throws EntityNotFoundException{
		productService.delete(sku);
		return ResponseEntity.ok().build();
	}

}
