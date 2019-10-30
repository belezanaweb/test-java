package br.com.blz.testjava.controller;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;
import br.com.blz.testjava.validation.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/api/v1/products")
@Validated
public class ProductController {

    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{sku}")
    public ResponseEntity<Product> getOne(@PathVariable Integer sku) {
        Product product = this.service.getOne(sku);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Product> save(@Valid @RequestBody Product product) {
        this.service.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{sku}")
    public ResponseEntity<Product> update(@PathVariable Integer sku, @Validated(OnUpdate.class) @RequestBody Product product) {
        product.setSku(sku);
        this.service.update(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity delete(@PathVariable Integer sku) {
        this.service.delete(sku);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
