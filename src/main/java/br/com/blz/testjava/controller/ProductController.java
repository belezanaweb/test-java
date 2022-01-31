package br.com.blz.testjava.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dto.ResponseDTO;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createProduct(@RequestBody @Valid Product product) throws Exception {
		return service.createProduct(product);        
    }

    @GetMapping
    public List<Product> listAllProducts() {
        return service.listAll();
    }

    @GetMapping("/{sku}")
    public Product findProductBySKU(@PathVariable Long sku) throws ProductNotFoundException {
        return service.getProductBySKU(sku);
    }

    @PutMapping("/{sku}")  
    public ResponseDTO updateById(@PathVariable Long sku, @RequestBody @Valid Product product) throws ProductNotFoundException {
        return service.updateProduct(sku, product);
    }

    @DeleteMapping("/{sku}")    
    public ResponseDTO deleteById(@PathVariable Long sku) throws ProductNotFoundException {
    	return service.deleteProduct(sku);
    }
	

}
