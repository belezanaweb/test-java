package br.com.blz.testjava.controller;

import br.com.blz.testjava.controller.request.ProductRequest;
import br.com.blz.testjava.controller.response.ProductResponse;
import br.com.blz.testjava.service.ProductService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProductController {
    
    private static final String PRODUCTS = "/products";
    private static final String PRODUCTS_SKU = PRODUCTS + "/{sku}";
    
    @Autowired
    private ProductService productService;
    
    @PostMapping(value = PRODUCTS, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody @Valid ProductRequest productRequest) {
        return productService.save(productRequest);
    }
    
    @PutMapping(value = PRODUCTS, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse update(@RequestBody @Valid ProductRequest productRequest) {
        return productService.update(productRequest);
    }
    
    @GetMapping(PRODUCTS_SKU)
    public ProductResponse getBySku(@PathVariable("sku") Long sku) {
        return productService.findBySku(sku);
    }
    
    @DeleteMapping(PRODUCTS_SKU)
    public void delete(@PathVariable("sku") Long sku) {
        productService.remove(sku);
    }
    
}
