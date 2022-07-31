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
    public Product getProduct(@PathVariable("sku") Integer sku){

        return new Product();
    }

    @DeleteMapping(value="/{sku}")
    public void deleteProduct(@PathVariable("sku") Integer sku) {

    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {

        productService.saveProduct(product);

    }

    @PutMapping(value="{sku}/")
    public void createProduct(@PathVariable("sku") Integer sku, @RequestBody Product product) {

    }
}
