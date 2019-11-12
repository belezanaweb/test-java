package br.com.blz.testjava.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.exception.ApiException;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping(value = "/marketplace/product/v1")
public class ProductController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductService productService;

	@CrossOrigin
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDto> saveNewProduct(@RequestBody(required = true) ProductDto productDto) throws ApiException {
		logger.info("saveNewProduct - PARAM product: {}", productDto);
		return new ResponseEntity<>(productService.saveProduct(productDto, true), HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDto> getProductBySku(@RequestParam(value = "sku", required = true) Long sku)
			throws ApiException {
		logger.info("getProductBySku - PARAM sku: {}", sku);
		return new ResponseEntity<>(productService.getProductBySku(sku), HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDto> updateProduct(@RequestBody(required = true) ProductDto productDto) throws ApiException {
		logger.info("updateProduct - PARAM product: {}", productDto);
		return new ResponseEntity<>(productService.saveProduct(productDto, false), HttpStatus.OK);
	}

	@CrossOrigin
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteProductBySku(@RequestParam(value = "sku", required = true) Long sku)
			throws ApiException {
		logger.info("deleteProductBySku - PARAM sku: {}", sku);
		return new ResponseEntity<>(productService.deleteProductBySku(sku), HttpStatus.OK);
	}

}
