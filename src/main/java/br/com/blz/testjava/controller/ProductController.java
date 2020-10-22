package br.com.blz.testjava.controller;

import br.com.blz.testjava.dto.ProductRequestDTO;
import br.com.blz.testjava.dto.ProductResponseDTO;
import br.com.blz.testjava.dto.ProductUpdateRequestDTO;
import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @ApiOperation(value = "API para criar novos produtos")
    public ResponseEntity<ProductResponseDTO>
        createProduct(@RequestBody @Valid ProductRequestDTO product) throws ProductAlreadyExistsException {
        log.info("Start -  createProduct.....");
        log.debug("Creating Product: {}", product);
        ProductResponseDTO productResponseDTO = productService.createProduct(product);
        log.info("Product Created.....");
        log.debug("ProductResponse: {}", productResponseDTO);
        log.info("End -  createProduct.....");
        return new ResponseEntity<ProductResponseDTO>(productResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{sku}")
    @ApiOperation(value = "API para Buscar produtos pelo SKU")
    public ResponseEntity<ProductResponseDTO>
    findProductBySku(@PathVariable  Long sku) throws ProductNotFoundException {
        log.info("Start -  findProductBySku.....");
        ProductResponseDTO productResponseDTO = productService.findProductBySku(sku);
        log.info("Product Found.....");
        log.info("End -  findProductBySku.....");
        return new ResponseEntity<ProductResponseDTO>(productResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(path="/{sku}")
    @ApiOperation(value = "API para Deletar produtos pelo SKU")
    public ResponseEntity<ProductResponseDTO> deleteProduct( @PathVariable Long sku) throws ProductNotFoundException {
        log.info("Start -  deleteProduct.....");
        ProductResponseDTO productResponseDTO = productService.deleteProduct(sku);
        log.info("Product Deleted .....");
        log.info("End -  deleteProduct.....");
        return new ResponseEntity<ProductResponseDTO>(productResponseDTO, HttpStatus.OK);
    }

    @PutMapping(path="/{sku}")
    @ApiOperation(value = "API para Atualizar Produtos")
    public ResponseEntity<ProductResponseDTO> updateProduct( @PathVariable Long sku,
                                                             @RequestBody @Valid ProductUpdateRequestDTO productUpdate) throws ProductNotFoundException {
        log.info("Start -  updateProduct.....");
        log.debug("Product Update Request -  sku: {} - product:{}",sku, productUpdate);
        ProductResponseDTO productResponseDTO = productService.updateProduct(sku,productUpdate);
        log.info("Product Updated .....");
        log.debug("ProductResponse: {}", productResponseDTO);
        log.info("End -  updateProduct.....");
        return new ResponseEntity<ProductResponseDTO>(productResponseDTO, HttpStatus.OK);
    }


}
