package br.com.blz.testjava.domain.services

import br.com.blz.testjava.application.exception.DataConstraintException
import br.com.blz.testjava.domain.entities.Product
import br.com.blz.testjava.domain.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService
@Autowired constructor(private val productRepository: ProductRepository) {
  fun findAll(): List<Product> = productRepository.findAll()
  fun find(sku: Long): Product? = productRepository.find(sku)
  fun delete(product: Product) = productRepository.delete(product)

  fun update(product: Product): Product {
    return productRepository.update(product)
  }

  fun save(product: Product): Product {
    if (productRepository.find(product.sku) == null) {
      return productRepository.save(product)
    } else {
     throw DataConstraintException("A product with the sku ${product.sku} already exists.")
    }
  }

}
