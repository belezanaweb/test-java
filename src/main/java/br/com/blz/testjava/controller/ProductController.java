package br.com.blz.testjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.exception.BusinessException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;

/**
 * Class responsible for managing api restful requests
 * 
 * @author Ramon Malaquias
 *
 */
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	/**
	 * 
	 * Endpoint to register a new product. 
	 * If a new product is registered but with an already registered SKU, an exception of type BusinessException will be thrown.
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/product/save", method = RequestMethod.POST)
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		try {
			productService.save(product);		
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (BusinessException be) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();			
		}
	}
	
	/**
	 * 
	 * Endpoint to update a product. 
	 * If the update is not possible, an exception of type BusinessException or ProductNotFoundException will be thrown.
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/product/edit", method = RequestMethod.PUT)
	public ResponseEntity<String> editProduct(@RequestBody Product product) {
		try {
			productService.editBySku(product);		
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (BusinessException | ProductNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();			
		}
	}
	
	/**
	 * 
	 * Endpoint to get a product by SKU number.
	 * If the product is not found, an exception of type BusinessException or ProductNotFoundException will be thrown.
	 * 
	 * @param sku
	 * @return
	 */
	@RequestMapping(value = "/product/{sku}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable  long sku) {
		try {	
			Product p = productService.findBySku(sku);
			return p;
		} catch (BusinessException | ProductNotFoundException e) {
			return null;			
		}
	}
	
	/**
	 * 
	 * Endpoint to update a product. 
	 * If the delete is not possible, an exception of type BusinessException or ProductNotFoundException will be thrown.
	 * 
	 * @param sku
	 * @return
	 */
	@RequestMapping(value = "/product/delete/{sku}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProduct(@PathVariable  long sku) {
		try {
			productService.deleteBySku(sku);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (BusinessException | ProductNotFoundException be) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();			
		}
	}
	
}
