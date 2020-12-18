package br.com.blz.testjava.api.controllers;

import br.com.blz.testjava.api.dtos.ProductDTO;
import br.com.blz.testjava.api.vos.ProductVO;
import br.com.blz.testjava.model.entities.Product;
import br.com.blz.testjava.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("{sku}")
    public ResponseEntity<ProductDTO> findBySku(@PathVariable Long sku){
         return ResponseEntity.ok(new ProductDTO(service.findBySku(sku)));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductVO vo, UriComponentsBuilder uriBuilder){
        Product product = service.save(vo);
        URI uri = uriBuilder.path("/products/{sku}").buildAndExpand(product.getSku()).toUri();
        return ResponseEntity.created(uri).body(new ProductDTO(product));
    }

    @DeleteMapping("{sku}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long sku){
        service.delete(sku);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{sku}")
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductVO vo, @PathVariable Long sku){
        return ResponseEntity.ok(new ProductDTO(service.update(vo, sku)));
    }

}
