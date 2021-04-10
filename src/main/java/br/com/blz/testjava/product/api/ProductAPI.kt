package br.com.blz.testjava.product.api

import br.com.blz.testjava.product.api.dto.ProductAPISaveInputDTO
import br.com.blz.testjava.product.api.dto.ProductAPISaveOutputDTO
import br.com.blz.testjava.product.api.dto.ProductAPISaveOutputDTOError
import br.com.blz.testjava.product.api.dto.ProductAPISaveOutputDTOSuccess
import br.com.blz.testjava.product.business.ProductBusiness
import br.com.blz.testjava.product.exception.ProductBusinessException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController
@RequestMapping(value = ["/products"])
class ProductAPI(
  @Autowired val productBusiness: ProductBusiness
) {
  @PostMapping
  fun create(@RequestBody payload: ProductAPISaveInputDTO) : ResponseEntity<ProductAPISaveOutputDTO> {
    return try {
      productBusiness.create(payload.toProduct())

      ResponseEntity.status(HttpStatus.CREATED).body(ProductAPISaveOutputDTOSuccess())
    } catch (e: ProductBusinessException) {
      ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ProductAPISaveOutputDTOError(listOf(e.message)))
    } catch (e: Exception) {
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ProductAPISaveOutputDTOError(listOf(e?.message ?: "Not expected error")))
    }
  }
}
