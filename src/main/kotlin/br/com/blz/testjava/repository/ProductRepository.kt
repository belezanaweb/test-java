package br.com.blz.testjava.repository

import br.com.blz.testjava.model.Product
import java.util.*

interface ProductRepository {
  fun create(product: Product): Product

  fun find(sku: Long): Product

  fun update(product: Product, sku: Long): Product

  fun delete(sku: Long)
}
