package br.com.blz.testjava.service

import br.com.blz.testjava.exception.ProductAlreadyExistsException
import br.com.blz.testjava.exception.ProductNotFoundException
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(@Autowired private val productRepository: ProductRepository) {
  fun getProductBySku(sku: Long): Product {

    val optionalProduct = productRepository.get(sku)

    optionalProduct?.let {
      optionalProduct.inventory.quantity = optionalProduct.inventory.calculateQuantity()
      optionalProduct.marketable = optionalProduct.isMarketable()
      return optionalProduct
    }
      ?: throw ProductNotFoundException("Product with sku [${sku}] was not found")
  }

  fun createProduct(product: Product): Product {

    if (productAlreadyExists(product.sku)) {
      throw ProductAlreadyExistsException("Product with sku [${product.sku}] already exists")
    }
    return productRepository.create(product)
  }

  private fun productAlreadyExists(sku: Long): Boolean {
    return productRepository.get(sku) != null
  }

  fun updateProduct(sku: Long, product: Product): Product {
    return productRepository.update(sku, product)
  }

  fun deleteProduct(sku: Long) {
    productRepository.delete(sku)
  }
}
