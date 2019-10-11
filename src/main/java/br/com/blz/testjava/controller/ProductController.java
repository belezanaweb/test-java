package br.com.blz.testjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.service.ProductService;


@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity product(@RequestParam(value = "sku") String sku) {
		ProductDTO prod = null;
		try {
			prod = productService.search(sku);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		if(prod==null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product not found");
		}
		return ResponseEntity.ok(prod);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity create(@RequestBody ProductDTO product) {
		ProductDTO prod = null;
		try {
			prod = productService.create(product);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		if(prod==null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product exists already");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(prod);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/product", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity update(@RequestBody ProductDTO product) {
		ProductDTO prod = null;
		try {
			prod = productService.update(product);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		if(prod==null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product does not exists");
		}
		return ResponseEntity.ok(prod);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/product", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity delete(@RequestParam(value = "sku") String sku) {
		ProductDTO prod = null;
		try {
			prod = productService.delete(sku);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		if(prod==null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product can not be deleted");
		}
		return ResponseEntity.ok(prod);
	}
}