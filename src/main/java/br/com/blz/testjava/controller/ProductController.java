package br.com.blz.testjava.controller;

import br.com.blz.testjava.controller.request.ProductRequest;
import br.com.blz.testjava.controller.response.ProductResponse;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.mapper.ProductMapper;
import br.com.blz.testjava.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest product){
        Product created = productService.save(ProductMapper.toDomain(product));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toResponse(created));
    }

    @GetMapping(path = "/{sku}")
    public ResponseEntity<ProductResponse> findBySku(@PathVariable Long sku){
        Product found = productService.findBySku(sku);
        return ResponseEntity.ok(ProductMapper.toResponse(found));
    }

    @PutMapping(path = "/{sku}")
    public ResponseEntity<ProductResponse> update(@RequestBody @Valid ProductRequest product, @PathVariable Long sku){
        Product updated = productService.update(ProductMapper.toDomain(product));
        return ResponseEntity.ok(ProductMapper.toResponse(updated));
    }

    @DeleteMapping(path = "/{sku}")
    public ResponseEntity delete(@PathVariable Long sku){
        productService.delete(sku);
        return ResponseEntity.ok().build();
    }
}
