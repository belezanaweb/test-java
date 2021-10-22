package br.com.blz.testjava.product.service

import br.com.blz.testjava.product.converters.toEntity
import br.com.blz.testjava.product.dto.ProductRequestDTO
import br.com.blz.testjava.product.exceptions.ProductExistentException
import br.com.blz.testjava.product.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class ProductService (@Autowired private val productRepository: ProductRepository) {

  fun create(product: ProductRequestDTO) {
      validateExistent (product.sku)
    productRepository.create(product.toEntity())
  }

  private fun validateExistent(sku: Long) {
      if (productRepository.findBySku(sku).isPresent) {
        throw ProductExistentException("Já existe um produto com essa SKU")
      }
  }
}
