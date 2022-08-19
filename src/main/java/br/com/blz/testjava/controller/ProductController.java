package br.com.blz.testjava.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.execption.ProductAlreadyExistException;
import br.com.blz.testjava.execption.ProductNotFoundException;
import br.com.blz.testjava.execption.SKUCoudNotBeChangedException;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<?>  findAll() {
		List<ProductDTO> products = productService.findAll();
		
		if (products.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(products);
	}
	
	@GetMapping("/{sku}")
	public ResponseEntity<?> findBySku(@PathVariable Long sku) {
		Optional<ProductDTO> product = productService.findBySku(sku);
		
		if (product.isPresent()) {
			return ResponseEntity.ok().body(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid ProductDTO product) {
		try {
			productService.save(product);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (ProductAlreadyExistException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{sku}")
	public ResponseEntity<?> update(@PathVariable(required = true) Long sku, @RequestBody ProductDTO product) {
		try {
			productService.update(sku, product);
			return ResponseEntity.ok().build();
		} catch (SKUCoudNotBeChangedException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (ProductNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{sku}")
	public ResponseEntity<?> delete(@PathVariable Long sku) {
		boolean deteled = productService.delete(sku);
		
		if (deteled) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
