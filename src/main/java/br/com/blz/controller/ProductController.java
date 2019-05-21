package br.com.blz.controller;

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

import br.com.blz.domain.Product;
import br.com.blz.service.ProductService;

/**
 * 
 * @author Anderson Makecio
 *
 */
@RestController
@RequestMapping(path = "/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<String> postProduct(@RequestBody Product prod) {
		
		try {
			
			productService.save(prod);		
			return ResponseEntity.status(HttpStatus.CREATED).build();
			
		} catch (Exception e) {			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();			
		}
		
	}
	
	@PutMapping
	public ResponseEntity<String> putProduct(@RequestBody Product prod) {
		
		try {
			
			productService.updateBySku(prod);	
			return ResponseEntity.status(HttpStatus.OK).build();
			
		} catch (Exception e) {			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();			
		}
		
	}

	@GetMapping(value = "{sku}")
	public Product getProduct(@PathVariable  long sku) {
		
		try {	
			
			return productService.findBySku(sku);
		
		} catch (Exception e) {
			return null;			
		}
	}
	
	@DeleteMapping(value = "{sku}")
	public ResponseEntity<String> deleteProduct(@PathVariable long sku) {
		
		try {
			
			productService.deleteBySku(sku);
			return ResponseEntity.status(HttpStatus.OK).build();
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();			
		}
		
	}
	
}
