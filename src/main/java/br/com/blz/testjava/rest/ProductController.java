package br.com.blz.testjava.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.business.ProductBusiness;
import br.com.blz.testjava.business.model.ProductInventoryBO;
import br.com.blz.testjava.rest.model.request.ProductInventoryRequest;
import br.com.blz.testjava.rest.model.response.ProductInventoryResponse;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductBusiness productBusiness;
	
	@GetMapping
	public ResponseEntity<ProductInventoryResponse> getProduct(@RequestParam("sku") Long sku) {		
		ProductInventoryBO product = this.productBusiness.find(sku);		
		return product == null 
				? ResponseEntity.noContent().build() 
				: ResponseEntity.ok(new ProductInventoryResponse(product));
	}
	
	@PostMapping
	public ResponseEntity<Void> postProduct(@RequestBody @Valid ProductInventoryRequest product) {		
		this.productBusiness.create(product);			
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<Void> putProduct(@RequestBody @Valid ProductInventoryRequest product) {		
		this.productBusiness.update(product);		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteProduct(@RequestParam("sku") Long sku) {		
		this.productBusiness.delete(sku);		
		return ResponseEntity.noContent().build();
	}
	
}
