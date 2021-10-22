package br.com.blz.testjava.product.repository

import br.com.blz.testjava.product.model.Product
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ProductRepository {

  companion object {
     val mapProducts = mutableMapOf<Long, Product>()
  }

  fun findBySku(sku: Long): Optional<Product> {
    return Optional.ofNullable(mapProducts[sku])
  }

  fun create(product: Product) {
    mapProducts[product.sku] = product
  }

}
