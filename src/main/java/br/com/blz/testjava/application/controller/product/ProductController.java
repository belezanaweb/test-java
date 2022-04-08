package br.com.blz.testjava.application.controller.product;

import br.com.blz.testjava.application.dto.product.ProductDto;
import br.com.blz.testjava.application.dto.product.ProductForm;
import br.com.blz.testjava.product.Product;
import br.com.blz.testjava.product.ProductRepository;
import br.com.blz.testjava.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ProductController {

    private ProductService productService;

    private ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody ProductForm productForm) throws NoSuchFieldException {
        Product product = productForm.converte();
        productService.saveProduct(product);

        return ResponseEntity.status(201).body(product);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> findAllProducts(){
        List<ProductDto> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{sku}")
    public ResponseEntity<ProductDto> findProductsBySku(@PathVariable Long sku){
        ProductDto product = productService.findBySkuProduct(sku);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/product/{sku}")
    public ResponseEntity<Long> updateProduct(@PathVariable Long sku, @RequestBody ProductForm productForm) throws NoSuchFieldException {
        productService.updateProduct(sku, productForm);
        return ResponseEntity.status(200).body(sku);
    }

    @DeleteMapping("/product/{sku}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long sku){
        productService.deleteProduct(sku);
        return ResponseEntity.ok("Deletado com sucesso");
    }
}
