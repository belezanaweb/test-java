package br.com.blz.testjava.cache

import br.com.blz.testjava.model.Product


object ProductCacheContext {
  private val productCacheMap = hashMapOf<Long, Product>()

  @Synchronized fun get(sku: Long): Product? {
    return productCacheMap[sku]
  }

  @Synchronized fun save(product: Product): Product {
    productCacheMap[product.sku] = product
    return product
  }

  @Synchronized fun update(sku: Long ,product: Product): Product {
    productCacheMap[sku] = product
    return product
  }

  @Synchronized fun delete(sku: Long) {
    productCacheMap.remove(sku)
  }

  @Synchronized fun clear() {
    productCacheMap.clear()
  }
}
