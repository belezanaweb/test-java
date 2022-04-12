package br.com.blz.testjava

import br.com.blz.testjava.model.Product
import java.util.concurrent.ConcurrentHashMap

class ProductDao {

  private val storage = ConcurrentHashMap<Int, Product>()

  fun create(product: Product) {
    storage.putIfAbsent(product.sku, product)
  }

  fun update(product: Product) {
    storage.put(product.sku, product)
  }

  fun delete(sku: Int) {
    storage.remove(sku)
  }

  fun get(sku: Int): Product? {
    return storage[sku]
  }

}
