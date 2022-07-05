package br.com.blz.testkotlin.repository

import br.com.blz.testkotlin.entity.ProductEntity
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImpl : ProductRepository {

  var products = mutableListOf<ProductEntity>()

  override fun save(productEntity: ProductEntity): Boolean {
    return products.add(productEntity)
  }

  override fun findBySku(sku: Long): ProductEntity? {
    return products.find { it.sku == sku }
  }


  override fun delete(sku: Long): Boolean {
    return products.removeIf { it.sku == sku }
  }

  override fun getAll(): MutableList<ProductEntity>? {
    return products
  }

  override fun update(productEntity: ProductEntity) {
    findBySku(productEntity.sku)?.apply {
      this.name = productEntity.name
      this.inventory.warehouses = productEntity.inventory.warehouses
    }
  }

}
