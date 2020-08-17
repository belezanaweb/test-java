package br.com.blz.testjava.controller;

import br.com.blz.testjava.domain.request.ProductCreateRequest;
import br.com.blz.testjava.domain.request.ProductUpdateRequest;
import br.com.blz.testjava.domain.response.ProductResponse;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductCreateRequest productCreateRequest,
                                                         UriComponentsBuilder uriBuilder) {
        ProductResponse product = productService.createProduct(productCreateRequest);
        URI uri = uriBuilder.path("/product/{sku}").buildAndExpand(product.getSku()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @PutMapping("/{sku}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long sku,
                                                         @RequestBody ProductUpdateRequest productUpdateRequest) {
        return ResponseEntity.ok(productService.updateProduct(sku, productUpdateRequest));
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long sku) {
        return ResponseEntity.ok(productService.getProduct(sku));
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long sku) {
        productService.deleteProduct(sku);
        return ResponseEntity.ok().build();
    }
}
