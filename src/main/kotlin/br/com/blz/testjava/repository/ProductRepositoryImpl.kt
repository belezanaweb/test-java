package br.com.blz.testjava.repository

import br.com.blz.testjava.cache.ProductCacheContext
import br.com.blz.testjava.exception.ProductAlreadyExistsException
import br.com.blz.testjava.model.Product
import org.springframework.stereotype.Repository

@Repository
internal class ProductRepositoryImp : ProductRepository {
  override fun getProductBySku(sku: Long): Product? {
    return ProductCacheContext.getProduct(sku)
  }

  override fun create(product: Product): Product {
    if (ProductCacheContext.getProduct(product.sku) != null) {
      ProductCacheContext.create(product)
    } else throw ProductAlreadyExistsException("Product with SKU ${product.sku} already exists")
    return product
  }
}
