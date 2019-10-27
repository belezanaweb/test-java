/**
 * 
 */
package br.com.blz.testjava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.constant.ProductConstant;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.exception.ProductException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;
import io.swagger.annotations.Api;

/**
 * @author ocean
 *
 */
@Api
@RequestMapping("api/v1/product")
@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(path =  "by-sku/{sku}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable("sku") Long sku) throws ProductException{
		return ResponseEntity.ok(this.productService.getProductBy(sku));
	}
	
	@GetMapping(path =  "list")
	public ResponseEntity<List<ProductDTO>> getProducts(){
		return ResponseEntity.ok(this.productService.getProducts());
	}
	
	@PostMapping("create")
	public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO) throws ProductException{		
		
		return ResponseEntity.ok(this.productService.create(productDTO));
	}
	
	@PutMapping("update")
	public ResponseEntity<Product> update(@RequestBody ProductDTO productDTO) throws ProductException{		
		
		return ResponseEntity.ok(this.productService.update(productDTO));
	}
	
	@DeleteMapping("delete/{sku}")
	public ResponseEntity<String> delete (@PathVariable("sku") Long sku) throws ProductException {
		this.productService.delete(sku);
		return ResponseEntity.ok(ProductConstant.MSG_SUCCESS);		
	}

}
