package com.boticario.testkotlin.application.response

import com.boticario.testkotlin.domain.entity.Inventory
import com.boticario.testkotlin.domain.entity.Product
import java.io.InvalidObjectException

data class ProductResponse(
        val sku: Long,
        val name: String,
        val inventory: InventoryResponse,
        val isMarketable: Boolean
) {
  companion object {
    fun fromDomain(product: Product) = ProductResponse(
      product.sku,
      product.name,
      InventoryResponse.fromDomain(product.inventory),
      product.isMarketable)
  }
}
