package br.com.blz.testjava.api.controller;

import br.com.blz.testjava.api.domain.Product;
import br.com.blz.testjava.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product) {
        service.save(product);
    }

    @GetMapping("/{sku}")
    public Product getProduct(@PathVariable("sku") Integer sku) {
        return service.findProductBySku(sku);
    }

    @PutMapping("/{sku}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody Product product, @PathVariable("sku") Integer sku) {
        service.update(product, sku);
    }

    @DeleteMapping("/{sku}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("sku") Integer sku) {
        service.delete(sku);
    }
}
