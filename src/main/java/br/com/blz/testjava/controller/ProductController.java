package br.com.blz.testjava.controller;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;


@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{sku}")
    public Product findProduct(@PathVariable(value = "sku") Integer sku){
        return productService.findProduct(sku);
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/product/{sku}")
    public Product modifyProduct(@PathVariable(value = "sku") Integer sku, @RequestBody Product product){
        return productService.modifyProduct(sku, product);
    }

    @DeleteMapping("/product/{sku}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "sku") Integer sku){
        return productService.deleteProduct(sku);
    }

}
