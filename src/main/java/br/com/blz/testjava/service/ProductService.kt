package br.com.blz.testjava.service

import br.com.blz.testjava.converter.ProductResquestToResponseConverter
import br.com.blz.testjava.model.ProductRequest
import br.com.blz.testjava.model.ProductResponse
import br.com.blz.testjava.repo.ProductRepo
import org.springframework.stereotype.Service

@Service
class ProductService(
  private val productRepo: ProductRepo,
  private val productResquestToResponseConverter: ProductResquestToResponseConverter
) {

  fun getProduct(sku: Long): ProductResponse {
    val sku = productRepo.getProduct(sku)
    return productResquestToResponseConverter.build(sku)
  }

  fun updateProduct(product: ProductRequest, sku: Long): ProductResponse {
    return productResquestToResponseConverter.build(productRepo.updateProduct(product, sku))
  }

  fun deleteProduct(sku: Long): Long {
    return productRepo.deleteProduct(sku)
  }

  fun insertProduct(product: ProductRequest): ProductResponse {
    return productResquestToResponseConverter.build(productRepo.insertProduct(product))
  }
}
