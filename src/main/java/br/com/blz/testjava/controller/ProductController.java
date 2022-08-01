package br.com.blz.testjava.controller;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")

public class ProductController {

    private ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value="/{sku}")
    public Product getProduct(final @PathVariable("sku") Integer sku){
        return this.productService.findBySku(sku);
    }

    @DeleteMapping(value="/{sku}")
    public void deleteProduct(final @PathVariable("sku") Integer sku) {
        this.productService.deleteProduct(sku);
    }

    @PostMapping
    public void createProduct(final @RequestBody Product product) {
        productService.insertProduct(product);
    }

    @PutMapping(value="{sku}/")
    public void editProduct(final @PathVariable("sku") Integer sku, final @RequestBody Product product) {
        productService.updateProduct(product);
    }
}
