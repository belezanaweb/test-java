package br.com.blz.testjava.controller;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Validated
    @PostMapping
    public void createProduct(final @Valid @RequestBody ProductDTO product) {
        productService.createProduct(product);
    }

    @PutMapping(value = "/{sku}")
    public void updateProduct(final @PathVariable("sku") Integer sku, final @RequestBody ProductDTO product) {
        productService.updateProduct(product,sku);
    }

    @GetMapping(value = "/{sku}")
    public ProductDTO recoveryProduct(final @PathVariable("sku") Integer sku) {
        return this.productService.recoveryProduct(sku);
    }

    @DeleteMapping(value = "/{sku}")
    public void deleteProduct(final @PathVariable("sku") Integer sku) {
        this.productService.deleteProduct(sku);
    }
}
