package br.com.blz.testjava.product.service

import br.com.blz.testjava.product.converters.toDTO
import br.com.blz.testjava.product.converters.toEntity
import br.com.blz.testjava.product.dto.ProductRequestDTO
import br.com.blz.testjava.product.dto.ProductResponseDTO
import br.com.blz.testjava.product.exceptions.ProductExistentException
import br.com.blz.testjava.product.exceptions.ProductNotFoundException
import br.com.blz.testjava.product.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(@Autowired private val productRepository: ProductRepository) {

  /**
   * Cria um novo produto
   * caso já existe, lança uma exception
   */
  fun create(product: ProductRequestDTO) {
    validateExistent(product.sku)
    productRepository.save(product.toEntity())
  }

  /**
   * Procura o produto no repositório e atualiza,
   * caso não encontre lança uma exception
   */
  fun update(sku: Long, product: ProductRequestDTO) {
    val existent = productRepository.findBySku(sku)
    if (existent.isPresent)
      productRepository.save(product.toEntity())
    else
      throw productNotFound(sku)
  }

  /**
   * Retorna um produto atráves da SKU,
   * caso não encontre retorna uma exception
   */
  fun getBySku(sku: Long): ProductResponseDTO {
    val existent = productRepository.findBySku(sku)
    if (existent.isPresent)
      return existent.get().toDTO()
    throw productNotFound(sku)
  }

  /**
   * Recupera um produto por sku e exclui,
   * Caso não encontre retorna uma exception
   */
  fun deleteProduct(sku: Long) {
    val existent = productRepository.findBySku(sku)
    if (existent.isPresent) {
      productRepository.deleteBySku(sku)
    } else
      throw productNotFound(sku)
  }

  private fun productNotFound(sku: Long): ProductNotFoundException{
    return ProductNotFoundException("Produto $sku não encontrado no cadastro")
  }

  private fun validateExistent(sku: Long) {
    if (productRepository.findBySku(sku).isPresent) {
      throw ProductExistentException("Já existe um produto cadastrado com essa SKU: $sku")
    }
  }

}
