package br.com.blz.testjava.application.web.controller.request

import br.com.blz.testjava.domain.entity.Product
import br.com.blz.testjava.domain.entity.Warehouse
import java.io.Serializable

data class ProductResponse(
  val sku: Int,
  val name: String,
  val inventory: InventoryRequest,
  val isMarketable: Boolean
): Serializable {
  companion object {
    fun fromDomain(product: Product, warehouseList: List<Warehouse>): ProductResponse {
      val quantity = warehouseList.sumOf { it.quantity }

      return ProductResponse(
        sku = product.sku,
        name = product.name,
        inventory = InventoryRequest(
          quantity = quantity,
          warehouses = warehouseList.map { WarehouseRequest.fromDomain(it) }
        ),
        isMarketable = quantity > 0
      )
    }
  }
}
