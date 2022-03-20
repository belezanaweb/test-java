package br.com.blz.testjava.repository

import br.com.blz.testjava.model.Product
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository {

  fun save(product: Product): Boolean
  fun update(product: Product): Product
  fun findBySku(sku: Long): Product
  fun delete(sku: Long): Boolean

}
