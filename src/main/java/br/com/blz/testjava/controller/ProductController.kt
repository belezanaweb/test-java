package br.com.blz.testjava.controller

import br.com.blz.testjava.model.ProductRequest
import br.com.blz.testjava.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("product")
class ProductController(
  private val productService: ProductService
) {

  @PostMapping("/")
  fun createSku(@RequestBody productRequest: ProductRequest): ResponseEntity<Any> {
    return try {
      val skuResponse = productService.insertProduct(productRequest)
      ResponseEntity(skuResponse, HttpStatus.CREATED)
    } catch (ex: Exception) {
      ResponseEntity("Já existe uma Produto com o mesmo SKU", HttpStatus.BAD_REQUEST)
    }
  }

  @PutMapping("/{sku}")
  fun updateSku(@RequestBody productRequest: ProductRequest,
                @PathVariable("sku") sku: Long): ResponseEntity<Any> {
    return try {
      val skuResponse = productService.updateProduct(productRequest, sku)
      ResponseEntity(skuResponse, HttpStatus.CREATED)
    } catch (ex: Exception) {
      ResponseEntity("Não foi possível encontrar um Produto com o sku $sku", HttpStatus.NOT_FOUND)
    }
  }

  @GetMapping("/{sku}")
  fun getSku(@PathVariable("sku") sku: Long): ResponseEntity<Any> {
    return try {
      val skuResponse = productService.getProduct(sku)
      ResponseEntity(skuResponse, HttpStatus.OK)
    } catch (ex: Exception) {
      ResponseEntity("Não foi possível encontrar um Produto com o sku $sku", HttpStatus.NOT_FOUND)
    }
  }

  @DeleteMapping("/{sku}")
  fun deleteSku(@PathVariable("sku") sku: Long): ResponseEntity<Any> {
    return try {
      productService.deleteProduct(sku)
      ResponseEntity(HttpStatus.NO_CONTENT)
    } catch (ex: Exception) {
      ResponseEntity("Não foi possível deletar o Produto com o sku $sku", HttpStatus.NOT_FOUND)
    }
  }
}
