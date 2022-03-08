package br.com.blz.testjava.controller;

import br.com.blz.testjava.dto.in.ProductDTOIn;
import br.com.blz.testjava.dto.out.ProductDTOOut;
import br.com.blz.testjava.exceptions.ProductFoundException;
import br.com.blz.testjava.exceptions.ProductNotFoundException;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProductDTOIn productDTOIn) throws ProductFoundException {
        this.productService.create(productDTOIn);
    }

    @GetMapping(path = "/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTOOut get(@PathVariable int sku) throws ProductNotFoundException {
        return this.productService.get(sku);
    }

    @DeleteMapping(path = "/{sku}")
    public void remove(@PathVariable int sku) {
        this.productService.remove(sku);
    }

    @PutMapping(path = "/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTOOut update(@PathVariable int sku, @RequestBody ProductDTOIn productDTOIn) throws ProductNotFoundException {
        return this.productService.update(sku, productDTOIn);
    }

}
