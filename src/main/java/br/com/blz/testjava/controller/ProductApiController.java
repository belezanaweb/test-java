/**
 * 
 */
package br.com.blz.testjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.exception.ProductNotFoudException;
import br.com.blz.testjava.service.ProductService;

/**
 * @author Augusto Lemes
 * @since 16/06/2019
 *
 */

@RestController
@RequestMapping("/api")
public class ProductApiController {
	
	@Autowired
	private ProductService productService;
	
	
	@RequestMapping(path="/save", method = RequestMethod.POST, consumes="application/json")
	public void save(@RequestBody ProductDTO product) {
		productService.save(product);
	}
	
	@GetMapping("/find/{sku}")
	public ProductDTO findOne(@PathVariable Long sku) throws ProductNotFoudException{
		return productService.findOne(sku);
	}
	
	@DeleteMapping("/delete/{sku}")
	public void delete(@PathVariable Long sku) throws ProductNotFoudException{
		productService.delete(sku);
	}
	
	

}
