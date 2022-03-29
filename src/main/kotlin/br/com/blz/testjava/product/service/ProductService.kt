package br.com.blz.testjava.product.service

import br.com.blz.testjava.exception.DomainBusinessException
import br.com.blz.testjava.exception.EntityNotFoundException
import br.com.blz.testjava.product.dto.ProductInDTO
import br.com.blz.testjava.product.dto.ProductOutDTO
import br.com.blz.testjava.product.mapper.ProductMapper
import br.com.blz.testjava.product.repository.IProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: IProductRepository) : IProductService {

  override fun create(productInDTO: ProductInDTO): ProductOutDTO {
    if (productRepository.existsBySku(productInDTO.sku)) {
      throw DomainBusinessException("Não foi possível criar, produto já existente")
    }

    val product = ProductMapper.toEntity(productInDTO)
    productRepository.save(product)
    return ProductMapper.toOutDto(product)
  }

  override fun editBySku(sku: Long, productInDTO: ProductInDTO): ProductOutDTO {
    val product = ProductMapper.toEntity(productInDTO)
    product.sku = sku
    productRepository.save(product)
    return ProductMapper.toOutDto(product)
  }

  override fun findBySku(sku: Long): ProductOutDTO {
    val product = productRepository.findBySku(sku)
      .orElseThrow { throw EntityNotFoundException("Produto não encontrado") }
    return ProductMapper.toOutDto(product)
  }

  override fun deleteBySku(sku: Long) {
    productRepository.deleteBySku(sku)
  }
}
