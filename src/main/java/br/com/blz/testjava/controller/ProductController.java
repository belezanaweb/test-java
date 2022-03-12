package br.com.blz.testjava.controller;

import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.form.ProductForm;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value ="/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody @Valid ProductForm form) throws ProductAlreadyExistsException {
        productService.create(form);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{sku}")
    public Product find(@PathVariable @Valid Long sku) throws ProductNotFoundException {
        return productService.find(sku);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void update(@RequestBody @Valid ProductForm form) throws ProductNotFoundException {
        productService.update(form);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{sku}")
    public void delete(@PathVariable @Valid Long sku) throws ProductNotFoundException {
        productService.delete(sku);
    }

}
