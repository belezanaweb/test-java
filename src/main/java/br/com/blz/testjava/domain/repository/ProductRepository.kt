package br.com.blz.testjava.domain.repository

import br.com.blz.testjava.domain.entity.Product

interface ProductRepository {
  fun save(product: Product): Product

  fun findById(sku: String): Product?

  fun existsById(sku: Int): Boolean

  fun deleteById(sku: Int)
}
