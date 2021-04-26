package br.com.blz.testjava.domain.services

import br.com.blz.testjava.domain.entities.Product
import br.com.blz.testjava.domain.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService
@Autowired constructor(private val productRepository: ProductRepository) {
  fun findAll(): List<Product> = productRepository.findAll()
  fun find(sku: Long): Product? = productRepository.find(sku)
  fun update(product: Product): Product = productRepository.update(product)
  fun delete(product: Product) = productRepository.delete(product)
  fun save(product: Product): Product = productRepository.save(product)
}
