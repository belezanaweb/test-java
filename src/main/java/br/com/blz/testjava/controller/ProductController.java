package br.com.blz.testjava.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.api.ProductApi;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;

@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
public class ProductController implements ProductApi {
	
	@Autowired
	ProductService service;
	
	public ResponseEntity createProduct(@Valid @RequestBody Product body) {
		return new ResponseEntity<>(service.postProduct(body), HttpStatus.CREATED); 
	}
	
	public ResponseEntity getProductBySKU(@PathVariable("sku") Long sku) {
		return new ResponseEntity<>(service.getProductBySKU(sku), HttpStatus.OK);
	}
	
	public ResponseEntity updateProduct(@Valid @RequestBody Product body) {
		Product updatedItem = service.updateProduct(body);
		return new ResponseEntity<>("Product with id "+updatedItem.getSku()+" was updated", HttpStatus.OK);
	}
	
	public ResponseEntity deleteProduct(@PathVariable("sku") Long sku) {
		return new ResponseEntity<>(service.deleteProduct(sku), HttpStatus.OK);
	}
	
	@GetMapping(value = "/product/testGetAll", produces = { "application/json" })
	public ResponseEntity getAllSkus() {
		return new ResponseEntity<>(service.testGetAll(), HttpStatus.OK);
	}
}
