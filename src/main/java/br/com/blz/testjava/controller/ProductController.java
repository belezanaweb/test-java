package br.com.blz.testjava.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//import br.com.blz.testjava.application.ProductService;
import br.com.blz.testjava.domain.model.Product;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping("/{sku}")
  public Product findBySku(@PathVariable Long sku) {
    return productService.findBySku(sku);
  }

  @GetMapping
  public List<Product> listAll() {
    return productService.listAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Product create(@Valid @RequestBody Product product) {
    return productService.create(product);
  }

  @PatchMapping("/{sku}")
  public Product update(@PathVariable Long sku, @Valid @RequestBody Product product) {
    product.setSku(sku);
    return productService.update(product);
  }

  @DeleteMapping("/{sku}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable Long sku) {
    productService.remove(sku);
  }
}
