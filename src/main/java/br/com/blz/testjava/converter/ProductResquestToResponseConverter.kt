package br.com.blz.testjava.converter

import br.com.blz.testjava.model.ProductRequest
import br.com.blz.testjava.model.ProductResponse
import br.com.blz.testjava.model.Warehouse
import org.springframework.stereotype.Component

@Component
class ProductResquestToResponseConverter {

  fun build(productRequest: ProductRequest): ProductResponse {
    val quantity = getQuantity(productRequest.inventory.warehouses)
    productRequest.inventory.quantity = quantity
    return ProductResponse(
      sku = productRequest.sku,
      name = productRequest.name,
      inventory = productRequest.inventory,
      isMarketable = isMarketable(quantity)
    )
  }

  private fun isMarketable(quantity: Int): Boolean {
    return quantity > 0
  }

  private fun getQuantity(warehouses: List<Warehouse>): Int {
    return warehouses.sumBy { it.quantity }
  }
}
