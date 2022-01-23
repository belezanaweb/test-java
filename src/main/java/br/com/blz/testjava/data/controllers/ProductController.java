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
	public ResponseEntity<List<ProductDTO>> index() {
		return new ResponseEntity<List<ProductDTO>>(this.service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{sku}")
	public ResponseEntity<ProductDTO> get(@PathVariable("sku") String sku) {
		ProductDTO productFound = this.service.find(sku);
		
		if(productFound == null) {
			return new ResponseEntity<ProductDTO>(productFound, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ProductDTO>(productFound, HttpStatus.OK);
	}
	
	@PostMapping("/") 
	public ResponseEntity<String> post(@RequestBody ProductDTO prd){
		
		String skuInserted = this.service.add(prd);
		
		if(skuInserted.isEmpty()) {
			return new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>(skuInserted, HttpStatus.CREATED); 
		
	}
	
	@PutMapping("/{sku}")
	public ResponseEntity<String> put(@RequestBody ProductDTO prd, @PathVariable("sku") String sku) {
		String skuUpdated = this.service.update(prd, sku);
		if(skuUpdated.isEmpty()) {
			return new ResponseEntity<String>(sku, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(sku, HttpStatus.OK);
	}
	
	@DeleteMapping("/{sku}")
	public ResponseEntity<Boolean> delete(@PathVariable("sku") String sku) {
		
		return new ResponseEntity<Boolean>(this.service.delete(sku), HttpStatus.OK);
		
	}

	

}
