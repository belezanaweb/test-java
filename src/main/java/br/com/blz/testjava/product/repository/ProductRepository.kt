package br.com.blz.testjava.product.repository

import br.com.blz.testjava.product.model.Product
import org.springframework.stereotype.Component
import java.util.*

@Component
private class ProductRepository : IProductRepository {

  private val memoryDataBase: HashMap<Long, Product> = hashMapOf()

  override fun save(product: Product): Product {
    memoryDataBase[product.sku] = product
    return product
  }

  override fun existsBySku(sku: Long): Boolean {
    return memoryDataBase.containsKey(sku)
  }

  override fun findBySku(sku: Long): Optional<Product> {
    return Optional.ofNullable(memoryDataBase[sku])
  }

  override fun deleteBySku(sku: Long) {
    memoryDataBase.remove(sku)
  }
}
