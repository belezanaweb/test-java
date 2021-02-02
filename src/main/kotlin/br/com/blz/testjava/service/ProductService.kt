package br.com.blz.testjava.service

import br.com.blz.testjava.exception.ProductAlreadyExistsException
import br.com.blz.testjava.exception.ProductNotFoundException
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService {

  private val productRepository: ProductRepository = ProductRepository()

  fun find(sku: Int): Product {
    val product = productRepository.find(sku) ?: throw ProductNotFoundException(sku)
    process(product)
    return product
  }

  private fun process(product: Product) {
    val quantity = product.inventory.warehouses
      .map { it.quantity }
      .reduce { acc, i -> acc + i }
    product.inventory.quantity = quantity
    product.isMarketable = quantity != 0
  }

  fun save(product: Product): Product {
    val find = productRepository.find(product.sku)
    if (find != null && find.sku == product.sku) {
      throw ProductAlreadyExistsException(product.sku)
    }
    return productRepository.save(product)
  }

  fun update(product: Product): Product {
    return productRepository.update(product)
  }

  fun remove(sku: Int) {
    productRepository.remove(sku) ?: throw ProductNotFoundException(sku)
  }
}
