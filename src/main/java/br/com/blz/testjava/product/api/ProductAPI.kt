package br.com.blz.testjava.product.api

import br.com.blz.testjava.product.api.dto.*
import br.com.blz.testjava.product.business.ProductBusiness
import br.com.blz.testjava.product.exception.ProductBusinessException
import br.com.blz.testjava.product.exception.ProductNotFoundException
import org.slf4j.LoggerFactory
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
  private val logger = LoggerFactory.getLogger(javaClass)

  @PostMapping
  fun create(@RequestBody payload: ProductAPISaveInputDTO) : ResponseEntity<ProductAPISaveOutputDTO> {
    logger.info("Start product creation {}", payload)

    return try {
      productBusiness.create(payload.toProduct())

      logger.info("Product created {}", payload)
      ResponseEntity.status(HttpStatus.CREATED).body(ProductAPISaveOutputSuccessDTO())
    } catch (e: ProductBusinessException) {
      logger.error("Product create error {}", e)
      ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ProductAPISaveOutputErrorDTO(listOf(e.message)))
    } catch (e: Exception) {
      logger.error("Product create error {}", e)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ProductAPISaveOutputErrorDTO(listOf(e?.message ?: "Unexpected error")))
    }
  }

  @PutMapping("/{sku}")
  fun update(@RequestBody payload: ProductAPISaveInputDTO, @PathVariable sku: Long) : ResponseEntity<ProductAPISaveOutputDTO> {
    logger.info("Start product update {}", payload)

    return try {
      productBusiness.update(payload.toProduct())

      logger.info("Product updated {}", payload)
      ResponseEntity.ok(ProductAPISaveOutputSuccessDTO())
    } catch (e: ProductNotFoundException) {
      logger.error("Error on update product {}", e)
      ResponseEntity.notFound().build()
    } catch (e: ProductBusinessException) {
      logger.error("Error on update product {}", e)
      ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ProductAPISaveOutputErrorDTO(listOf(e.message)))
    } catch (e: Exception) {
      logger.error("Error on update product {}", e)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ProductAPISaveOutputErrorDTO(listOf(e?.message ?: "Unexpected error")))
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
    logger.info("Start product delete. sku: {} $sku")

    return try {
      productBusiness.delete(sku)

      logger.info("Product deleted {}")
      ResponseEntity.ok(ProductAPIDeleteOutputSuccessDTO())
    } catch (e: ProductNotFoundException) {
      logger.error("Error on delete product {}", e)
      ResponseEntity.status(HttpStatus.NOT_FOUND).body(ProductAPIDeleteOutputErrorDTO(listOf(e.message!!)))
    } catch (e: Exception) {
      logger.error("Error on delete product {}", e)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }
  }
}
