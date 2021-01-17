package br.com.blz.testjava.controller;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.exceptions.ExistentProductException;
import br.com.blz.testjava.exceptions.InexistentProductException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Validated ProductDTO product) {
        Product responseProduct = productService.createProduct(product);
        ResponseEntity<Product> response = new ResponseEntity<>(responseProduct, HttpStatus.CREATED);
        return response;
    }

    @PutMapping("/{sku}")
    public ResponseEntity<Product> updateProduct(@PathVariable("sku") Integer sku,
                                                 @RequestBody @Validated ProductDTO product) {
        Product responseProduct = productService.editProduct(sku, product);
        ResponseEntity<Product> response = new ResponseEntity<>(responseProduct, HttpStatus.CREATED);
        return response;
    }

    @GetMapping("/{sku}")
    public ResponseEntity<Product> findBySKU(@PathVariable("sku") Integer sku) {
        Product responseProduct = productService.findProductBySKU(sku);
        ResponseEntity<Product> response = new ResponseEntity<>(responseProduct, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<String> deleteBySku(@PathVariable("sku") Integer sku) {
        productService.deleteProductBySku(sku);
        return new ResponseEntity<>("Produto excluído com sucesso",HttpStatus.OK);
    }

    @ExceptionHandler(ExistentProductException.class)
    public ResponseEntity<String> existentProductHandler(RuntimeException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InexistentProductException.class)
    public ResponseEntity<String> inexistentProductHandler(RuntimeException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> oops(RuntimeException exception){
        return new ResponseEntity<>("oops, algo deu errado...", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
