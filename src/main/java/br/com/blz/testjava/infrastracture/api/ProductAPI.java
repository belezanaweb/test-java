package br.com.blz.testjava.infrastracture.api;

import br.com.blz.testjava.infrastracture.product.models.CreateProductRequest;
import br.com.blz.testjava.infrastracture.product.models.ProductResponse;
import br.com.blz.testjava.infrastracture.product.models.UpdateProductRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/products")
public interface ProductAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createProduct(@RequestBody CreateProductRequest input);

    @GetMapping(value = "/{sku}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ProductResponse getBySku(@PathVariable(name = "sku") Long sku);

    @PutMapping(value = "/{sku}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updateBySku(@PathVariable(name = "sku") Long sku, @RequestBody UpdateProductRequest input);

    @DeleteMapping(value = "/{sku}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBySku(@PathVariable(name = "sku") Long sku);
}
