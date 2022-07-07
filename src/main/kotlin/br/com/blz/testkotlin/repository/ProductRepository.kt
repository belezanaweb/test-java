package br.com.blz.testkotlin.repository

import br.com.blz.testkotlin.entity.ProductEntity
import org.springframework.stereotype.Repository

@Repository
class ProductRepository {

  var products = mutableListOf<ProductEntity>()

  fun save(productEntity: ProductEntity): Boolean {
    return products.add(productEntity)
  }

  fun findBySku(sku: Long): ProductEntity? {
    return products.find { it.sku == sku }
  }

  fun delete(sku: Long): Boolean {
    return products.removeIf { it.sku == sku }
  }

  fun getAll(): MutableList<ProductEntity>? {
    return products
  }

  fun update(productEntity: ProductEntity) {
    findBySku(productEntity.sku)?.apply {
      this.name = productEntity.name
      this.inventory.warehouses = productEntity.inventory.warehouses
    }
  }
}
