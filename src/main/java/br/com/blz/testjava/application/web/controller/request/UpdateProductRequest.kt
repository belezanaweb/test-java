package br.com.blz.testjava.application.web.controller.request

import br.com.blz.testjava.domain.entity.UpdateProduct
import java.io.Serializable

data class UpdateProductRequest(
  val name: String,
  val inventory: InventoryRequest
): Serializable {

  fun toDomain(sku: Int) = UpdateProduct(
    sku = sku,
    name = name,
    inventory = inventory.warehouses.map { it.toDomain() }
  )
}
