package br.com.blz.testjava.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.Product;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@GetMapping("/{sku}")
	public ResponseEntity<Product> getBySky(@PathVariable("sku") Integer sku) {
		return ResponseEntity.ok().build();
	}
	
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable("sku") Integer sku, @RequestBody Product product) {
		return null;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("sku") Integer sku) {
		return ResponseEntity.notFound().build();
	}
}
