package br.com.blz.testjava.controller

import br.com.blz.testjava.application.exception.DataConstraintException
import br.com.blz.testjava.controller.dto.ProductDTO
import br.com.blz.testjava.domain.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
  fun create(@Valid @RequestBody productDTO: ProductDTO, br: BindingResult): ResponseEntity<ProductDTO> {
    checkConstraintValidation(br)
    return ResponseEntity.status(HttpStatus.CREATED).body(ProductDTO(productService.save(productDTO.toEntity())))
  }

  @PutMapping("{sku}")
  fun update(@Valid @RequestBody productDTO: ProductDTO,
             @PathVariable("sku") sku: Long,
             br: BindingResult): ResponseEntity<ProductDTO> {
    checkConstraintValidation(br)
    productDTO.sku = sku
    return if (productService.find(sku) == null) {
      create(productDTO, br)
    } else {
      ResponseEntity.ok(ProductDTO(productService.update(productDTO.toEntity())))
    }
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

  private fun checkConstraintValidation(br: BindingResult) {
    if (br.hasErrors()) {
      throw DataConstraintException(br)
    }
  }


}
