package br.com.blz.testjava.product.repository

import br.com.blz.testjava.product.model.Product
import java.util.*

interface IProductRepository {
  fun save(product: Product): Product
  fun existsBySku(sku: Long): Boolean
  fun findBySku(sku: Long): Optional<Product>
  fun deleteBySku(sku: Long)
}
