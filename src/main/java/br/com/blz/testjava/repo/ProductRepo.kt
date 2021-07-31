package br.com.blz.testjava.repo

import br.com.blz.testjava.model.ProductRequest
import org.springframework.stereotype.Component

@Component
class ProductRepo(
  private var products: MutableList<ProductRequest>
) {

  fun insertProduct(product: ProductRequest): ProductRequest {
    val index = getIndex(product.sku)
    if(index == -1) {
      products.add(product)
      return product
    } else {
      throw Exception()
    }
  }

  fun getProduct(sku: Long): ProductRequest {
    val index = getIndex(sku)
    return products[index]
  }

  fun updateProduct(product: ProductRequest, sku: Long): ProductRequest {
    try {
        val index = getIndex(sku)
        products[index] = product
        return product
    } catch (ex: Exception) {
      throw Exception()
    }
  }

  fun deleteProduct(sku: Long): Long {
    try {
      val index = getIndex(sku)
      products.removeAt(index)
      return sku
    } catch (ex: Exception) {
      throw Exception()
    }
  }

  private fun getIndex(sku: Long): Int {
    return products.indexOfFirst { it.sku == sku }
  }
}
