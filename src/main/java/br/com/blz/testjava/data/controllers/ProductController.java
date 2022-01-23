package br.com.blz.testjava.data.controllers;

import java.util.List;

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


import br.com.blz.testjava.data.dto.ProductDTO;
import br.com.blz.testjava.data.dto.ProductListDTO;
import br.com.blz.testjava.data.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	private ProductService service;

	public ProductController(ProductService service) {
		super();
		this.service = service;
	}

	@GetMapping("/")
	public ResponseEntity<List<ProductListDTO>> index() {
		return new ResponseEntity<List<ProductListDTO>>(this.service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{sku}")
	public ResponseEntity<ProductDTO> get(@PathVariable("sku") String sku) {
		ProductDTO productFound = null;
		
		try {
			productFound = this.service.find(sku);
			
			if(productFound == null) {
				return new ResponseEntity<ProductDTO>(productFound, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			 e.printStackTrace(); //should be logged (splunk, file, database or any other place)
			 return new ResponseEntity<ProductDTO>(productFound, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<ProductDTO>(productFound, HttpStatus.OK);
	}
	
	@PostMapping("/") 
	public ResponseEntity<String> post(@RequestBody ProductDTO prd){
		
		String skuInserted = "";
		try {
			skuInserted = this.service.add(prd);
			if(skuInserted.isEmpty()) {
				return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception e) {
			e.printStackTrace(); //should be logged (splunk, file, database or any other place)
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); 
			
		}
		
		return new ResponseEntity<String>(skuInserted, HttpStatus.CREATED); 
	}
	
	@PutMapping("/{sku}")
	public ResponseEntity<String> put(@RequestBody ProductDTO prd, @PathVariable("sku") String sku) {
		String skuUpdated = "";
		
		try {
			skuUpdated = this.service.update(prd, sku);
			if(skuUpdated.isEmpty()) {
				return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace(); //should be logged (splunk, file, database or any other place)
			return new ResponseEntity<String>("Something went wrong, try again latter.", HttpStatus.BAD_REQUEST); 
		}
		
		return new ResponseEntity<String>(sku, HttpStatus.OK);
	}
	
	@DeleteMapping("/{sku}")
	public ResponseEntity<Boolean> delete(@PathVariable("sku") String sku) {
		
		return new ResponseEntity<Boolean>(this.service.delete(sku), HttpStatus.OK);
		
	}

	

}
