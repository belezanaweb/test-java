package br.com.blz.testkotlin.service

import br.com.blz.testkotlin.entity.ProductEntity

interface ProductService {
  fun saveProduct(product: ProductEntity): ProductEntity?
  fun editProduct(product: ProductEntity): ProductEntity?
  fun getProductBySku(sku: Long): ProductEntity?
  fun deleteProductBySku(sku: Long)
  fun getAllProducts(): MutableList<ProductEntity>?
}
