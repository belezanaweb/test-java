package br.com.blz.testjava.controller;

import br.com.blz.testjava.controller.data.ProductAttributes;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "/", tags = "Produto", description = "Api responsável por gerenciar produtos")
@RestController
@Slf4j
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Busca um produto por SKU")
    @GetMapping("/product/{sku}")
    public Product findProductBySku(@PathVariable("sku") Long sku)
    {
        log.info("Buscando produto por sku:{}", sku);
        return productService.findProductBySku(sku);
    }

    @ApiOperation(value = "Realiza a criação de um novo produto")
    @PutMapping("/product")
    public ResponseEntity<String> addProduct( @RequestBody @Valid ProductAttributes productAttributes){
        log.info("Realizando a criação de um novo produto = {}", productAttributes);
        productService.createProduct(productAttributes);
        return ResponseEntity.status(HttpStatus.OK).body("Produto criado com sucesso!");
    }

    @ApiOperation(value = "Realiza a remoção de um produto por SKU")
    @DeleteMapping("product/{sku}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long sku) {
        log.info("Removendo produto atraves do sku {} ", sku);
        productService.deleteProduct(sku);
        return ResponseEntity.status(HttpStatus.OK).body("Product removido com sucesso");
    }

    @ApiOperation(value = "Realiza a atualização de um produto por SKU")
    @PatchMapping("/product/{sku}")
    public ResponseEntity<String> updateProduct( @PathVariable Long sku, @RequestBody @Valid ProductAttributes productAttributes) {
        log.info("Atualizando produto sku: {} com : {}", sku, productAttributes);
        productService.update(sku, productAttributes);
        return new ResponseEntity<String>("Produto atualizado com sucesso", HttpStatus.CREATED);
    }
}
