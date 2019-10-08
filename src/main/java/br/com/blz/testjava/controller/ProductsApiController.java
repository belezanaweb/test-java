package br.com.blz.testjava.controller;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsApiController {

    @Autowired
    ProductCrudService service;

    @PostMapping
    public final ResponseEntity<Object> createProduct(@Valid @RequestBody Product product) throws Exception {

        service.createProduct(product);

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{sku}")
    public final ResponseEntity<Object> getProduct(@PathVariable int sku) {

        return ResponseEntity.ok(service.getProduct(sku));
    }

    @DeleteMapping("/{sku}")
    public final ResponseEntity<Object> deleteProduct(@PathVariable int sku) {

        service.deleteProduct(sku);

        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{sku}")
    public final ResponseEntity<Object> updateProduct(@PathVariable int sku, @RequestBody Product product) {

        service.updateProduct(sku, product);

        return ResponseEntity.accepted().build();
    }

}
