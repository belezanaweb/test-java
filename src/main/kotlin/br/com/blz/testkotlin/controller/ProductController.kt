package br.com.blz.testkotlin.controller

import br.com.blz.testkotlin.entity.ProductEntity
import br.com.blz.testkotlin.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/product")
class ProductController(
  val productService: ProductService
) {

  @PostMapping
  fun createProduct(@RequestBody productEntity: ProductEntity): ResponseEntity<ProductEntity>{
    if (productService.getProductBySku(productEntity.sku) != null) {
      return ResponseEntity(productEntity,HttpStatus.CONFLICT)
    }
    var product = productService.saveProduct(productEntity)
    return ResponseEntity<ProductEntity>(product, HttpStatus.CREATED)
  }

  @GetMapping("/{sku}")
  fun getProduct(
    @PathVariable("sku")
    sku: Long
  ): ResponseEntity<ProductEntity>{
    return ResponseEntity(productService.getProductBySku(sku), HttpStatus.OK)
  }

  @GetMapping("/all")
  fun getAllProducts(): ResponseEntity<MutableList<ProductEntity>>{
    return ResponseEntity(productService.getAllProducts(), HttpStatus.OK)
  }

  @PutMapping("/{sku}")
  fun editProduct(
    @PathVariable("sku")
    sku: Long,
    @RequestBody
    productDto: ProductEntity
  ): ResponseEntity<ProductEntity>{
    return ResponseEntity( productService.editProduct(productDto), HttpStatus.OK)
  }

  @DeleteMapping("/{sku}")
  fun deleteProduct(
    @PathVariable("sku")
    sku: Long
  ): ResponseEntity<Any> {
    return ResponseEntity(productService.deleteProductBySku(sku), HttpStatus.OK)
  }
}
