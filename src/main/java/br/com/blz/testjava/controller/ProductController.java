package br.com.blz.testjava.controller;

import br.com.blz.testjava.controller.resources.ProductRequest;
import br.com.blz.testjava.controller.resources.ProductResponse;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/products", produces = APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/{sku}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("sku") Long sku) {
        return ok(productService.getProductBySku(sku));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequest request) {
        productService.createProduct(request);
        return new ResponseEntity<>(CREATED);
    }

    @PutMapping(path = "/{sku}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest request, @PathVariable Long sku) {
        return ok(productService.updateProduct(request, sku));
    }

    @DeleteMapping(value = "/{sku}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("sku") Long sku) {
        productService.deleteProduct(sku);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
