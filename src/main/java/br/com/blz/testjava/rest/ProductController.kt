package br.com.blz.testjava.rest

import br.com.blz.testjava.rest.dto.ProductDto
import br.com.blz.testjava.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/products")
class ProductController @Autowired constructor(
  private var productService: ProductService
) {

  @PostMapping
  fun postProduct(@RequestBody productDto: ProductDto): ResponseEntity<ProductDto> {
    val sucess = productService.createProduct(productDto.toProductModel())
    if (sucess) {
      return ResponseEntity(productDto, HttpStatus.OK)
    }
    return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
  }

  @PutMapping
  fun updateProduct(@RequestBody productDto: ProductDto): ResponseEntity<Any> {
    return try {
      val updateProduct = productService.updateProduct(productDto.toProductModel())
      ResponseEntity(ProductDto.fromProductModel(updateProduct), HttpStatus.OK)
    } catch (e: Exception) {
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(e.message)
    }
  }

  @GetMapping("/{sku}")
  fun findProductBySku(@PathVariable sku: Long): ResponseEntity<Any> {
    return try {
      val product = productService.findBySku(sku)
      ResponseEntity(ProductDto.fromProductModel(product), HttpStatus.OK)
    } catch (e: Exception) {
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(e.message)
    }
  }

  @DeleteMapping("/{sku}")
  fun deleteProductBySKu(@PathVariable sku: Long): ResponseEntity<Any> {
    return try {
      val sucess = productService.deleteProduct(sku)
      if (sucess) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    } catch (e: Exception) {
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(e.message)
    }
  }
}
