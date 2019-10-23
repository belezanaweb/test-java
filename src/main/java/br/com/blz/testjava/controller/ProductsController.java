package br.com.blz.testjava.controller;

import br.com.blz.testjava.controller.dto.ProductDTO;
import br.com.blz.testjava.exception.ResourceAlreadyExistsException;
import br.com.blz.testjava.exception.ResourceNotFoundException;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * REST Controller for handling products CRUD operations
 */
@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired private ProductService service;

    @GetMapping("/{sku}")
    public ResponseEntity<ProductDTO> findBy(
        @PathVariable("sku") final Long sku) throws ResourceNotFoundException {

        final ProductDTO product = service.findBy(sku);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(
        @RequestBody final ProductDTO product,
        final UriComponentsBuilder uriBuilder) throws ResourceAlreadyExistsException {

        final ProductDTO result = service.save(product);

        final URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getSku()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping("/{sku}")
    public ResponseEntity<ProductDTO> update(
        @PathVariable("sku") final Long sku,
        @RequestBody final ProductDTO product) throws ResourceNotFoundException {

        final ProductDTO result = service.update(sku, product);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> delete(
        @PathVariable("sku") final Long sku) throws ResourceNotFoundException {

        service.delete(sku);
        return ResponseEntity.ok().build();
    }
}
