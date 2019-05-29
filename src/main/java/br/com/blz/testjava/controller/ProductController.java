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
import br.com.blz.testjava.exception.BusinessException;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping(value="/v1/products", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/insert")
	public void insert(@RequestBody ProductDTO product) throws BusinessException{
		productService.insert(product);
	}

	@GetMapping("/findBySku/{sku}")
	public ResponseEntity<?> findBySku(@PathVariable("sku") Long sku) throws BusinessException{
		return ResponseEntity.ok(productService.find(sku));	 
	}

	@DeleteMapping("/delete/{sku}")
	public void deleteBySku(@PathVariable("sku") Long sku){
		productService.delete(sku);
	}
	
	@PutMapping("/updateBySku/{sku}")
	public ResponseEntity<?> updateBySku(@PathVariable("sku") Long sku, @RequestBody ProductDTO product) throws BusinessException{
		return ResponseEntity.ok(productService.update(sku,product));
	}

}
