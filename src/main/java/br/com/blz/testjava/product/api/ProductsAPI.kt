package br.com.blz.testjava.product.api

import br.com.blz.testjava.product.dto.ProductRequestDTO
import br.com.blz.testjava.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/products", produces = [MediaType.APPLICATION_JSON_VALUE])
class ProductsAPI (@Autowired private val productService: ProductService) {

  @PostMapping
  fun create(@RequestBody product: ProductRequestDTO): ResponseEntity<String> {
    productService.create(product)
    return ResponseEntity.status(200).build()
  }
}
