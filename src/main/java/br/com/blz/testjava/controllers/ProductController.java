package br.com.blz.testjava.controllers;


import br.com.blz.testjava.dtos.ProductDTO;
import br.com.blz.testjava.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "/v1/products")
    @ResponseStatus(CREATED)
    public void createProduct(@RequestBody @Valid ProductDTO productDTO) {
        productService.createProduct(productDTO);
    }

    @PutMapping(value = "/v1/products/{id}")
    public ProductDTO updateProduct(@PathVariable("id") long id, @RequestBody @Valid ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

    @GetMapping(value = "/v1/products/{id}")
    public ProductDTO getProduct(@PathVariable("id") long id) {
        return productService.getProduct(id);
    }

    @DeleteMapping(value = "/v1/products/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
    }

}
