package br.com.blz.testjava.repository

import br.com.blz.testjava.model.Product

interface ProductRepository {

  fun save(entity: Product): Product?
  fun findBySku(sku: Long): Product
  fun findAll(): List<Product?>
  fun delete(entity: Product)
  fun deleteAll()
}
