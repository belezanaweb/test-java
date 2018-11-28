package br.com.blz.testjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import br.com.blz.testjava.service.BlzWebProductService;

@RestController
@RequestMapping("products")
public class ProductsController {
	
	@Autowired
	BlzWebProductService blzService;

	@GetMapping(value = "/{sku}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductDTO> get(@PathVariable Long sku){
		
		ProductDTO dto = this.blzService.get(sku);
		return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO product){
		
		ProductDTO newProduct;
		newProduct = this.blzService.create(product);
		
		return ResponseEntity.ok(newProduct);
	}
	
	@PutMapping(value = "/{sku}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductDTO> update(@PathVariable Long sku, @RequestBody ProductDTO product){
		
		ProductDTO newProduct;
		newProduct = this.blzService.update(sku, product);
		
		return ResponseEntity.ok(newProduct);
	}
	
	@DeleteMapping("/{sku}")
	public void delete(@PathVariable Long sku){
		
		this.blzService.delete(sku);
	}
	

}
