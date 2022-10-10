package br.com.blz.testjava.cache

import br.com.blz.testjava.model.Product


object ProductCacheContext {

  private val productCacheMap = hashMapOf<Long, Product>()


  fun getProduct(sku: Long): Product? {
    return productCacheMap[sku]
  }

  fun create(product: Product) {
    productCacheMap[product.sku] = product
  }
}
