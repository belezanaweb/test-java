package br.com.blz.testjava.controllers;

import br.com.blz.testjava.models.Product;
import br.com.blz.testjava.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody Product product) {
        this.productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody Product product) {
        this.productService.update(product);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(path = "/{sku}")
    public ResponseEntity get(@PathVariable("sku") Long sku) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.findOne(sku));
    }

    @DeleteMapping(path = "/{sku}")
    public ResponseEntity delete(@PathVariable("sku") Long sku) {
        this.productService.delete(sku);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
