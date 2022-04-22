package br.com.blz.testjava.service

import br.com.blz.testjava.model.Product
import br.com.blz.testjava.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService (private val productRepository: ProductRepository){

  fun save(entity: Product) = productRepository.save(entity)
  fun findBySku(sku: Long) = productRepository.findBySku(sku)
  fun findAll()  = productRepository.findAll()
  fun delete(sku: Long) {
    val product = findBySku(sku)

    productRepository.delete(product)
  }
}
