package br.com.blz.testjava.controller

import br.com.blz.testjava.model.Product
import br.com.blz.testjava.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/product"])
class ProductController {

  private val productService = ProductService()

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun include(@RequestBody product: Product): Product {
    return productService.save(product);
  }

  @GetMapping("/{sku}")
  fun get(@PathVariable("sku") sku: Int): Product {
    return productService.find(sku)
  }

  @PutMapping("/{sku}")
  fun update(@PathVariable sku: Int, @RequestBody product: Product): Product {
    product.sku = sku
    return productService.save(product)
  }

  @DeleteMapping("/{sku}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun remove(@PathVariable("sku") sku: Int) {
    productService.remove(sku)
  }
}
