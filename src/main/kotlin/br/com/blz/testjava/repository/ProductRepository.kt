package br.com.blz.testjava.repository

import br.com.blz.testjava.model.Product
import org.springframework.stereotype.Service

@Service
interface ProductRepository {
  fun get(sku: Long): Product?
  fun create(product: Product): Product
  fun update(sku: Long, product: Product): Product
  fun delete(sku: Long)
}
