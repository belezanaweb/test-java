package br.com.blz.testjava.service

import br.com.blz.testjava.model.Product
import br.com.blz.testjava.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(@Autowired private val productRepository: ProductRepository) {
    fun getProductBySku(sku: Long): Product? {
      return productRepository.getProductBySku(sku)
    }

  fun createProduct(product: Product): Product {
    return productRepository.create(product)
  }
}
