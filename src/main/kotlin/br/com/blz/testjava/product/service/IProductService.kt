package br.com.blz.testjava.product.service

import br.com.blz.testjava.product.dto.ProductInDTO
import br.com.blz.testjava.product.dto.ProductOutDTO

interface IProductService {
  fun create(productInDTO: ProductInDTO): ProductOutDTO
  fun editBySku(sku: Long, productInDTO: ProductInDTO): ProductOutDTO
  fun findBySku(sku: Long): ProductOutDTO
  fun deleteBySku(sku: Long)
}
