package br.com.blz.testjava.controllers;


import br.com.blz.testjava.controllers.dto.request.ProductRequest;
import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/products", produces = { "application/vnd.testjava.api.v1+json" })
public class ProductController {

    @Autowired
    private transient ProductService productService;

    @Autowired
    private transient ModelMapper mapper;

    @PostMapping
    public ResponseEntity<ProductRequest> insert(@RequestBody @Valid ProductRequest body) {
        Product product = this.productService.insert(this.mapper.map(body, Product.class));
        ProductRequest response = this.mapper.map(product, ProductRequest.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
