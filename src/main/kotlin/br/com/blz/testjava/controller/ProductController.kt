package br.com.blz.testjava.controller

import br.com.blz.testjava.controller.dto.ProductDTO
import br.com.blz.testjava.domain.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("products")
class ProductController
  @Autowired constructor(private val productService: ProductService) {

  @GetMapping
  fun findAll(): ResponseEntity<List<ProductDTO>> =
    ResponseEntity.ok(productService.findAll().map { product -> ProductDTO(product) })

  @GetMapping("{sku}")
  fun findBySku(@PathVariable("sku") sku: Long): ResponseEntity<ProductDTO> {
    val product = productService.find(sku)
    return if (product == null) {
      ResponseEntity.notFound().build()
    } else {
      ResponseEntity.ok(ProductDTO(product))
    }
  }

  @PostMapping
  fun create(@RequestBody productDTO: ProductDTO): ResponseEntity<ProductDTO> {
    return ResponseEntity.status(HttpStatus.CREATED).body(ProductDTO(productService.save(productDTO.toEntity())))
  }

  @PutMapping
  fun update(@RequestBody productDTO: ProductDTO): ResponseEntity<ProductDTO> {
    return ResponseEntity.ok(ProductDTO(productService.update(productDTO.toEntity())))
  }

  @DeleteMapping("{sku}")
  fun delete(@PathVariable("sku") sku: Long): ResponseEntity<Any> {
    val product = productService.find(sku)
    return if (product == null) {
       ResponseEntity.notFound().build()
    } else {
      productService.delete(product)
      ResponseEntity.ok().build()
    }

  }


}
