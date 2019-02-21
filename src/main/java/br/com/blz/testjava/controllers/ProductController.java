package br.com.blz.testjava.controllers;


import br.com.blz.testjava.controllers.dto.request.ProductRequest;
import br.com.blz.testjava.controllers.dto.response.ProductResponse;
import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/{sku}")
    public ResponseEntity<ProductResponse> getBySku( @PathVariable Long sku) {
        Product product = this.productService.findBySku(sku)
            .orElseThrow(() -> new IllegalArgumentException("Não foi encontrado nenhum produto por este SKU"));
        ProductResponse response = this.mapper.map(product, ProductResponse.class);
        return ResponseEntity.ok(response);
    }

}
