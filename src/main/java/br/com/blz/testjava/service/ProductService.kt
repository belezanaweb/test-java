package br.com.blz.testjava.service

import br.com.blz.testjava.model.Product
import br.com.blz.testjava.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService @Autowired constructor(
  private val productRepository: ProductRepository
) {

  fun createProduct(product: Product): Boolean {
    return productRepository.save(product)
  }

  fun updateProduct(product: Product): Product {
    return productRepository.update(product)
  }

  fun findBySku(sku: Long): Product {
    return productRepository.findBySku(sku)
  }

  fun deleteProduct(sku: Long): Boolean {
    return productRepository.delete(sku)
  }
}
