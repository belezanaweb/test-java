package br.com.blz.testjava.product.controller

import br.com.blz.testjava.product.dto.ProductInDTO
import br.com.blz.testjava.product.dto.ProductOutDTO
import br.com.blz.testjava.product.service.IProductService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(private val productService: IProductService) {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun create(@RequestBody productInDTO: ProductInDTO): ProductOutDTO {
    return productService.create(productInDTO)
  }

  @PutMapping("/{sku}")
  @ResponseStatus(HttpStatus.OK)
  fun editBySku(@PathVariable sku: Long, @RequestBody productInDTO: ProductInDTO): ProductOutDTO {
    return productService.editBySku(sku, productInDTO)
  }

  @GetMapping("/{sku}")
  @ResponseStatus(HttpStatus.OK)
  fun findBySku(@PathVariable sku: Long): ProductOutDTO {
    return productService.findBySku(sku)
  }

  @DeleteMapping("/{sku}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun deleteBySku(@PathVariable sku: Long) {
    productService.deleteBySku(sku)
  }
}
