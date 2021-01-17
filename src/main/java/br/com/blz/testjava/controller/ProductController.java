package br.com.blz.testjava.controller;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody ProductDTO product) {
        Product responseProduct = productService.createProduct(product);
        return responseProduct;
    }
}
