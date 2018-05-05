package br.com.blz.testjava.controller;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.service.ProductService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createProduct(@RequestBody Product product) {
        service.create(product);
    }

    @GetMapping("/{sku}")
    public Product getProductBySku(@PathVariable  Integer sku) {
        return service.findBySku(sku);
    }

    @DeleteMapping("/{sku}")
    @ResponseStatus(NO_CONTENT)
    public void deleteProductBySku(@PathVariable Integer sku) {
        service.remove(sku);
    }

    @ResponseStatus(OK)
    @PutMapping("/{sku}")
    public void updateProduct(@PathVariable Integer sku, @RequestBody Product product) {
        service.updateProduct(sku, product);
    }
}
