package br.com.blz.testjava.application.web.controller

import br.com.blz.testjava.application.service.CreateProductService
import br.com.blz.testjava.application.service.DeleteProductService
import br.com.blz.testjava.application.service.GetProductService
import br.com.blz.testjava.application.service.UpdateProductService
import br.com.blz.testjava.application.web.controller.request.CreateProductRequest
import br.com.blz.testjava.application.web.controller.request.ProductResponse
import br.com.blz.testjava.application.web.controller.request.UpdateProductRequest
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(
  private val createProductService: CreateProductService,
  private val getProductService: GetProductService,
  private val deleteProductService: DeleteProductService,
  private val updateProductService: UpdateProductService
) {
  @GetMapping("/product/{sku}")
  fun getProductBySku(
    @PathVariable("sku") sku: String
  ): ProductResponse {
    return getProductService.getProduct(sku)
  }

  @PostMapping("/product")
  fun createProduct(
    @RequestBody createProductRequest: CreateProductRequest
  ): ProductResponse {
    return createProductService.createProduct(createProductRequest)
  }

  @DeleteMapping("/product/{sku}")
  fun deleteProduct(
    @PathVariable("sku") sku: String
  ) {
    deleteProductService.deleteProduct(sku)
  }

  @PutMapping("/product/{sku}")
  fun deleteProduct(
    @PathVariable("sku") sku: String,
    @RequestBody updateProductRequest: UpdateProductRequest
  ) {
    updateProductService.updateProduct(sku, updateProductRequest)
  }
}
