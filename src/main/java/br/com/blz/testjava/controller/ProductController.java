package br.com.blz.testjava.controller;

import br.com.blz.testjava.exceptions.ResourceNotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody @Valid Product product) {
        Product persisted = productService.save(product);
        return new ResponseEntity<>(persisted, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{sku}")
    public ResponseEntity<Product> findBySku(@PathVariable Long sku) {
        Optional<Product> product = Optional.ofNullable(productService
            .findBySku(sku)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found for given SKU")));

        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{sku}")
    public ResponseEntity<String> deleteBySku(@PathVariable Long sku) {
        productService.deleteBySku(sku);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/{sku}")
    public ResponseEntity<Product> update(@PathVariable Long sku, @RequestBody Product productToUpdate) {
        Optional<Product> persistedProduct = productService.findBySku(sku);

        if(!persistedProduct.isPresent()) {
            throw new ResourceNotFoundException("Product not found for given SKU");
        }

        Product updatedProduct = productService.update(productToUpdate);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

}
