package br.com.blz.testjava.controller;


import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products/test/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{sku}")
    public ResponseEntity<Void> updateProduct(@PathVariable Integer sku, @RequestBody Product updatedProduct) {
        productService.updateProduct(sku, updatedProduct);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{sku}")
    public ResponseEntity<Product> getProductBySku(@PathVariable Integer sku) {
        Product product = productService.getProductBySku(sku);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> deleteProductBySku(@PathVariable Integer sku) {
        productService.deleteProductBySku(sku);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
