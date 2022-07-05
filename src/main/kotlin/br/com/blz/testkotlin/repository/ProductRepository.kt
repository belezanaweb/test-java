package br.com.blz.testkotlin.repository

import br.com.blz.testkotlin.entity.ProductEntity

interface ProductRepository {

  fun save(productEntity: ProductEntity): Boolean
  fun findBySku(sku: Long): ProductEntity?
  fun delete(sku: Long): Boolean
  fun getAll(): MutableList<ProductEntity>?
  fun update(productEntity: ProductEntity)

}
