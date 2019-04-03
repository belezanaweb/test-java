package br.com.blz.testjava.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	public ResponseEntity deleteProduct(@PathVariable("sku") Long sku) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	public ResponseEntity getProductBySKU(@PathVariable("sku") Long sku) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	public ResponseEntity postProduct(@Valid @RequestBody Product body) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	public ResponseEntity updateProductWithForm(@Valid @RequestBody Product body) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
}
