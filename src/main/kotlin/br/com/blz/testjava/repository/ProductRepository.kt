package br.com.blz.testjava.repository

import br.com.blz.testjava.exception.ProductAlreadyExistsException
import br.com.blz.testjava.model.Product
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class ProductRepository {

  private val map: ConcurrentMap<Int, Product> = ConcurrentHashMap()

  fun find(sku: Int): Product? {
    return map[sku]
  }

  fun save(product: Product): Product {
    if (map.containsKey(product.sku)) {
      throw ProductAlreadyExistsException(product.sku)
    }
    map[product.sku] = product
    return product
  }

  fun remove(sku: Int): Product? {
    return map.remove(sku)
  }
}
