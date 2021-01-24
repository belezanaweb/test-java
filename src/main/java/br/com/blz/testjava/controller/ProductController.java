package br.com.blz.testjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.ProductException;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/{sku}")
	public ResponseEntity<Product> getBySku(@PathVariable(value = "sku") Long sku){
		
		Product product = productService.findBySku(sku);
		
		if(product != null)
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
    }
	
	@PostMapping
	public ResponseEntity<Product> post(@RequestBody Product resquestProduct) throws ProductException {
        Product responseProduct = productService.create(resquestProduct);
        ResponseEntity<Product> response = new ResponseEntity<>(responseProduct, HttpStatus.CREATED);
        
        return response;
    }
	
	@PutMapping("/{sku}")
    public String put() {
        return "@PutMapping";
    }
	
	@DeleteMapping("/{sku}")
    public String delete() {
        return "@DeleteMapping";
    }
	
}
