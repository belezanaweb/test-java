package br.com.blz.testjava.cache

import br.com.blz.testjava.model.Product


object ProductCacheContext {
  private val productCacheMap = ThreadLocal<HashMap<Long, Product>>()


  fun getProduct(sku: Long): Product? {
    return productCacheMap.get()[sku]
  }

  fun create(product: Product) {
    productCacheMap.get()[product.sku] = product
  }
}
