package br.com.blz.testjava.controller;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void post(@RequestBody Product product) {
        productService.create(product);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "{sku}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void put(@PathVariable Long sku, @RequestBody Product product) {
        product.setSku(sku);
        productService.update(product);
    }

    @RequestMapping(value = "/{sku}")
    public Product getBySku(@PathVariable Long sku) {
        return productService.getBySku(sku);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{sku}", method = RequestMethod.DELETE)
    public void deleteBySku(@PathVariable Long sku) {
        productService.deleteBySku(sku);
    }

}
