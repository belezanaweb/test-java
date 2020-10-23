package br.com.blz.testjava.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;



@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@ResponseBody
	@GetMapping()
	public ResponseEntity<?> get() {
		
		List<Product> product = null;
		product = productService.findAll();		
		return ResponseEntity.status(HttpStatus.OK).body(product);
		
	}
	
	@ResponseBody
	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable String id) {
		
		Product product = null;
		product = productService.findById(Long.valueOf(id));		
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
		
	}

	@PostMapping
	public ResponseEntity<Product> post(@RequestBody Product product) throws Exception {
		
		try {
			
			productService.create(product);
			
		} catch (Exception e) {
			
			throw new Exception("Product not created");
			
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	

	@PutMapping
	public ResponseEntity<Product> put(@RequestBody Product product) throws Exception {
		
		try {
			
			productService.updateProduct(product);
			
		} catch (Exception e) {
			
			
			throw new Exception("Product not changed");
			
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
		
		try {
			
			productService.deleteProduct(id);
			
		} catch (Exception e) {
			
			throw new Exception("Product not deleted");
			
		}
		return ResponseEntity.status(HttpStatus.OK).body("Product with sku deleted " + id );
	}
}
