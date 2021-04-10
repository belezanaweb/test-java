package br.com.blz.testjava.product.api

import br.com.blz.testjava.product.api.dto.*
import br.com.blz.testjava.product.business.ProductBusiness
import br.com.blz.testjava.product.exception.ProductBusinessException
import br.com.blz.testjava.product.exception.ProductNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@RestController
@RequestMapping(value = ["/"])
class ProductAPI(
  @Autowired val productBusiness: ProductBusiness
) {
  @PostMapping
  fun create(@RequestBody payload: ProductAPISaveInputDTO) : ResponseEntity<ProductAPISaveOutputDTO> {
    return try {
      productBusiness.create(payload.toProduct())

      ResponseEntity.status(HttpStatus.CREATED).body(ProductAPISaveOutputSuccessDTO())
    } catch (e: ProductBusinessException) {
      ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ProductAPISaveOutputErrorDTO(listOf(e.message)))
    } catch (e: Exception) {
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ProductAPISaveOutputErrorDTO(listOf(e?.message ?: "Not expected error")))
    }
  }

  @PutMapping("/{sku}")
  fun update(@RequestBody payload: ProductAPISaveInputDTO, @PathVariable sku: Long) : ResponseEntity<ProductAPISaveOutputDTO> {
    return try {
      productBusiness.update(payload.toProduct())
      ResponseEntity.ok(ProductAPISaveOutputSuccessDTO())
    } catch (e: ProductNotFoundException) {
      ResponseEntity.notFound().build()
    } catch (e: ProductBusinessException) {
      ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ProductAPISaveOutputErrorDTO(listOf(e.message)))
    } catch (e: Exception) {
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ProductAPISaveOutputErrorDTO(listOf(e?.message ?: "Not expected error")))
    }
  }

  @GetMapping("/{sku}")
  fun get(@PathVariable sku: Long) : ResponseEntity<ProductAPIGetOutputDTO> {
    return try {
      val product = productBusiness.get(sku)
      ResponseEntity.ok(ProductAPIGetOutputDTO(product))
    } catch (e: ProductNotFoundException) {
      ResponseEntity.notFound().build()
    } catch (e: Exception) {
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }
  }

  @DeleteMapping("/{sku}")
  fun delete(@PathVariable sku: Long) : ResponseEntity<ProductAPIDeleteOutputDTO> {
    return try {
      productBusiness.delete(sku)
      ResponseEntity.ok(ProductAPIDeleteOutputSuccessDTO())
    } catch (e: ProductNotFoundException) {
      ResponseEntity.status(HttpStatus.NOT_FOUND).body(ProductAPIDeleteOutputErrorDTO(listOf(e.message!!)))
    } catch (e: Exception) {
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }
  }
}
