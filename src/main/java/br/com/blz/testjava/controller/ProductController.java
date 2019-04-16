package br.com.blz.testjava.controller;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController(value = "produto")
public class ProductController {

    @Autowired
    public ProductService service;

    @PostMapping
    public Product save(@Valid @RequestBody Product product) throws Exception {
        return service.save(product);
    }

    @GetMapping(produces = "application/json")
    public List<Product> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{sku}", produces = "application/json")
    public Product find(@PathVariable("sku") String sku) {
        return service.find(sku);
    }

    @DeleteMapping(value = "/{sku}")
    public void delete(@PathVariable("sku") String sku) {
        service.remove(sku);
    }

    @PutMapping(value = "/{sku}", produces = "application/json")
    public Product update(@PathVariable("sku") String sku, @Valid @RequestBody Product product) {
        return service.update(sku, product);
    }
}
