package br.com.blz.testjava.controller;

import br.com.blz.testjava.domain.api.request.CreateProductRequest;
import br.com.blz.testjava.domain.api.request.ReplaceProductRequest;
import br.com.blz.testjava.domain.api.response.CreateProductResponse;
import br.com.blz.testjava.domain.api.response.ProductResponse;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<CreateProductResponse> create(@RequestBody @Valid CreateProductRequest request) {
        CreateProductResponse response = service.createProduct(request);
        return ResponseEntity.created(getLocation(request.getSku().toString())).body(response);
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductResponse> read(@PathVariable("sku") String sku) {
        return ResponseEntity.ok(service.findPrduct(sku));
    }

    @PutMapping("/{sku}")
    public ResponseEntity<ProductResponse> replace(@PathVariable("sku") String sku,
                                                   @RequestBody @Valid ReplaceProductRequest request) {
        ProductResponse response = service.updateProduct(sku, request);

        if (response.getUpdated()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.created(getLocation(sku)).body(response);
        }
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> delete(@PathVariable("sku") String sku) {
        service.deleteProduct(sku);
        return ResponseEntity.noContent().build();
    }

    private URI getLocation(@RequestBody @Valid String sku) {
        try {
            return new URI("/product/" + sku);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
