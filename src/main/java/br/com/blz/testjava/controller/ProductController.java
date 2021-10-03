package br.com.blz.testjava.controller;

import br.com.blz.testjava.model.Product;

import br.com.blz.testjava.exception.ProductDuplicateException;
import org.springframework.web.bind.annotation.*;
import br.com.blz.testjava.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService = new ProductService();

    @PostMapping("/")
    @ExceptionHandler(ProductDuplicateException.class)
    public Product appProduct(@RequestBody Product product) {
        return productService.insert(product);
    }
    @PutMapping("/{sku}")
    public Product updateProduct(@RequestBody Product product, @PathVariable("sku") Long sku) {
        return productService.update(product, sku);
    }

    @GetMapping("/{sku}")
    public Product getBySku(@PathVariable("sku") Long sku) {
        return productService.getBySku(sku);
    }

    @DeleteMapping("/{sku}")
    public List<Product> delete(@PathVariable("sku") Long sku) {
        return productService.delete(sku);
    }

    @GetMapping("/")
    public List<Product> getProducts() {
        return productService.getAll();
    }
}
