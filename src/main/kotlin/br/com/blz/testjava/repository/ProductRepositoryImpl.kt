package br.com.blz.testjava.repository

import br.com.blz.testjava.cache.ProductCacheContext
import br.com.blz.testjava.model.Product
import org.springframework.stereotype.Repository

@Repository
internal class ProductRepositoryImp : ProductRepository {
  override fun get(sku: Long): Product? {
    return ProductCacheContext.get(sku)
  }

  override fun create(product: Product): Product {
    return ProductCacheContext.save(product)
  }

  override fun update(sku: Long, product: Product): Product {
    val optionalProduct = get(sku)
    optionalProduct?.let { return ProductCacheContext.update(sku, product) } ?: return ProductCacheContext.save(product)
  }

  override fun delete(sku: Long) {
    ProductCacheContext.delete(sku)
  }
}
