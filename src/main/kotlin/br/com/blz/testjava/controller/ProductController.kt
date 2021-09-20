package br.com.blz.testjava.controller

import br.com.blz.testjava.exception.ProductException
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/products")
class ProductController(@Autowired var repository: ProductRepository) {

  @PostMapping
  fun create(@RequestBody product: Product): ResponseEntity<Product> = ResponseEntity.ok(repository.create(product))

  @GetMapping("/{sku}")
  fun find(@PathVariable sku: Long): ResponseEntity<Product> = ResponseEntity.ok(repository.find(sku))

  @PutMapping("/{sku}")
  fun update(@PathVariable sku: Long, @RequestBody product: Product): ResponseEntity<Product> = ResponseEntity.ok(repository.update(product, sku))

  @DeleteMapping("/{sku}")
  fun delete(@PathVariable sku: Long) = ResponseEntity.ok(repository.delete(sku))
}
