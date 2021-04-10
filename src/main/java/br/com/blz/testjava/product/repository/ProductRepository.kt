package br.com.blz.testjava.product.repository

import br.com.blz.testjava.product.business.objects.Product
import org.springframework.stereotype.Service

@Service
object ProductRepository {
  private val products: MutableList<Product> = mutableListOf()

  fun save(product: Product) = remove(product)
      .let { products.add(product) }
      .let { product }

  fun remove(product: Product) = products.removeIf { existent -> existent.sku == product.sku }
  fun get(sku: Long) = products.find { product -> product.sku == sku }
  fun hasProduct(sku: Long) = products.any { product -> product.sku == sku }
}
