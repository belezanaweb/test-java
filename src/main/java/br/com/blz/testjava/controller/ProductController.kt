package br.com.blz.testjava.controller

import br.com.blz.testjava.controller.request.ProductRequest
import br.com.blz.testjava.controller.request.ProductUpdateRequest
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.service.ProductService
import br.com.blz.testjava.toModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("products")
class ProductController(private val productService: ProductService) {

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  fun index() = productService.findAll()

  @GetMapping("/{sku}")
  @ResponseStatus(HttpStatus.OK)
  fun search(@PathVariable sku: Long) = productService.findBySku(sku)

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun save(@RequestBody @Valid request: ProductRequest) = productService.save(request.toModel())

  @PutMapping("/{sku}")
  @ResponseStatus(HttpStatus.OK)
  fun update(@RequestBody @Valid request: ProductUpdateRequest,
             @PathVariable sku: Long): Product? {

    val product = productService.findBySku(sku)

    return productService.save(request.toModel(product))
  }

  @DeleteMapping("/{sku}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun delete(@PathVariable sku: Long) = productService.delete(sku)
}
