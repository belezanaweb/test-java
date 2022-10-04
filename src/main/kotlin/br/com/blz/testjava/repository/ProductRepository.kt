package br.com.blz.testjava.repository

import br.com.blz.testjava.model.Product
import org.springframework.stereotype.Service

@Service
interface ProductRepository {
  fun getProductBySku(sku: Long): Product?
  fun create(product: Product): Product
}
