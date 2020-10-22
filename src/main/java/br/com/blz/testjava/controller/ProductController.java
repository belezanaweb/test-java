package br.com.blz.testjava.controller;

import br.com.blz.testjava.dto.ProductRequestDTO;
import br.com.blz.testjava.dto.ProductResponseDTO;
import br.com.blz.testjava.dto.ProductUpdateRequestDTO;
import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO>
        createProduct(@RequestBody @Valid ProductRequestDTO product) throws ProductAlreadyExistsException {
        ProductResponseDTO productResponseDTO = productService.createProduct(product);
        return new ResponseEntity<ProductResponseDTO>(productResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{sku}")
    public ResponseEntity<ProductResponseDTO>
    createProduct(@PathVariable  Long sku) throws ProductNotFoundException {
        ProductResponseDTO productResponseDTO = productService.findProductBySku(sku);
        return new ResponseEntity<ProductResponseDTO>(productResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(path="/{sku}")
    public ResponseEntity<ProductResponseDTO> deleteProduct( @PathVariable Long sku) throws ProductNotFoundException {
        ProductResponseDTO productResponseDTO = productService.deleteProduct(sku);
        return new ResponseEntity<ProductResponseDTO>(productResponseDTO, HttpStatus.OK);
    }

    @PutMapping(path="/{sku}")
    public ResponseEntity<ProductResponseDTO> updateProduct( @PathVariable Long sku,
                                                             @RequestBody @Valid ProductUpdateRequestDTO productUpdate) throws ProductNotFoundException {
        ProductResponseDTO productResponseDTO = productService.updateProduct(sku,productUpdate);
        return new ResponseEntity<ProductResponseDTO>(productResponseDTO, HttpStatus.OK);
    }


}
