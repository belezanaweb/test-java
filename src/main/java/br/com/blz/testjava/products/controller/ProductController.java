package br.com.blz.testjava.products.controller;

import br.com.blz.testjava.products.model.Product;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author pzatta
 */

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    protected ProductService service;

    @GetMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public Product get(@PathVariable("sku") long sku) {
        return service.findBySku(sku);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Product> get() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product post(@RequestBody Product product) {
        return service.create(product);
    }

    @PutMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public Product put(@PathVariable("sku") long sku, @RequestBody Product product) {
        return service.update(product);
    }

    @DeleteMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public void detele(@PathVariable("sku") long sku) {
        service.remove(sku);
    }
}
