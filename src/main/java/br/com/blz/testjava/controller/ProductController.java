package br.com.blz.testjava.controller;

import br.com.blz.testjava.controller.request.ProductRequest;
import br.com.blz.testjava.domain.entity.Product;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Set<Product>> findAll() {
       return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{sku}")
    public ResponseEntity<Product> findBySku(@PathVariable Long sku) {
        return new ResponseEntity<>(productService.findBySku(sku), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ProductRequest productRequest) {
        productService.create(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Product> update(@RequestBody ProductRequest productRequest) {
      return new ResponseEntity<>(productService.update(productRequest), HttpStatus.OK);
    }

    @DeleteMapping("{sku}")
    public ResponseEntity<Void> delete(@PathVariable Long sku) {

        productService.delete(sku);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
