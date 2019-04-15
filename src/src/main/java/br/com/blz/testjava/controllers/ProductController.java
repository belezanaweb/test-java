package br.com.blz.testjava.controllers;

import br.com.blz.testjava.models.Product;
import br.com.blz.testjava.repositories.ProductRepository;

import br.com.blz.testjava.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api")
@AllArgsConstructor
@Api(value = "Product", description = "Products")
@Log4j
@Controller
public class ProductController {

    private ProductService productService;


    @ApiOperation(value = "Get Product by SKU")
    @RequestMapping(value = "/product/{sku}",
                    method = RequestMethod.GET,
                    produces = "application/json")
    public ResponseEntity<Product> findProductBy(@Valid @PathVariable("sku") Long sku){
        Product found = productService.findBySku(sku);
        return ResponseEntity.status(HttpStatus.OK).body(found);
    }


    @ApiOperation(value = "Create Product")
    @RequestMapping(value = "/product/",
                    method = RequestMethod.POST,
                    consumes = "application/json",
                    produces = "application/json")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product values) {
        Product saved = productService.saveProduct(values);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @ApiOperation(value = "Update Product")
    @RequestMapping(value = "/product/",
        method = RequestMethod.PATCH,
        consumes = "application/json",
        produces = "application/json")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product values) {
        Product saved = productService.saveProduct(values);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }


    @ApiOperation(value = "Delete Product by SKU")
    @RequestMapping(value = "/product/{sku}",
        method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable("sku") Long sku) {
        productService.deleteProduct(sku);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
