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
	public ResponseEntity<Object> getBySku(@PathVariable(value = "sku") Long sku) throws ProductException{
		
		try {
			return new ResponseEntity<Object>(productService.findBySku(sku), HttpStatus.OK);
		}
		catch (ProductException e) {
			
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
    }
	
	@PostMapping
	public ResponseEntity<Object> post(@RequestBody Product resquestProduct) throws ProductException {
		
        try {
        	Product responseProduct = productService.create(resquestProduct);
            ResponseEntity<Object> response = new ResponseEntity<>(responseProduct, HttpStatus.CREATED);
            
            return response;
        }
		catch (ProductException e) {
			
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
		}
    }
	
	@PutMapping("/{sku}")
	public ResponseEntity<Object> put(@PathVariable(value = "sku") Long sku, @RequestBody Product resquestProduct) throws ProductException {

        try {
        	Product responseProduct = productService.update(sku, resquestProduct);
            ResponseEntity<Object> response = new ResponseEntity<>(responseProduct, HttpStatus.OK);
            
            return response;
        }
		catch (ProductException e) {
			
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
    }
	
	@DeleteMapping("/{sku}")
    public String delete() {
        return "@DeleteMapping";
    }
	
}
