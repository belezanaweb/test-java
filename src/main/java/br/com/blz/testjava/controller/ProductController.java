package br.com.blz.testjava.controller;

import javax.validation.Valid;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.DuplicationException;
import br.com.blz.testjava.exception.NotFoundException;
import br.com.blz.testjava.service.ProductService;
import br.com.blz.testjava.validation.group.Insertion;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public Product create(@Validated(value = { Default.class, Insertion.class }) @RequestBody Product product)
            throws DuplicationException {
        return productService.create(product);
    }

    @PutMapping("/products/{sku}")
    public Product update(@Valid @RequestBody Product product, @PathVariable Long sku) throws NotFoundException {
        return productService.update(product, sku);
    }

    @GetMapping("/products/{sku}")
    public Product get(@PathVariable Long sku) throws NotFoundException {
        return productService.get(sku);
    }

    @DeleteMapping("products/{sku}")
    public void delete(@PathVariable Long sku) {
        productService.delete(sku);
    }
}
