package br.com.blz.testjava.api.product.controller;

import br.com.blz.testjava.api.product.controller.domain.ProductRequest;
import br.com.blz.testjava.api.product.controller.domain.ProductResponse;
import br.com.blz.testjava.api.product.service.ProductService;
import br.com.blz.testjava.common.exceptions.ProductAlreadyExistsException;
import br.com.blz.testjava.common.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {

  private final ProductService service;

  @PostMapping
  public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest product)
      throws ProductAlreadyExistsException {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(product));
  }

  @GetMapping("{id}")
  public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") final Integer id)
      throws ProductNotFoundException {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.getProduct(id));
  }

  @PutMapping
  public ResponseEntity<ProductResponse> update(@RequestBody final ProductRequest request)
      throws ProductNotFoundException {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.update(request));
  }

  @DeleteMapping("{id}")
  public ResponseEntity delete(@PathVariable("id") final Integer id)
      throws ProductNotFoundException {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
