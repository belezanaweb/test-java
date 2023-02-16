package br.com.blz.testjava.application.web.controller.request

import br.com.blz.testjava.domain.entity.CreateProduct
import java.io.Serializable

data class CreateProductRequest(
  val sku: Int,
  val name: String,
  val inventory: InventoryRequest
): Serializable {

  fun toDomain() = CreateProduct(
    sku = sku,
    name = name,
    inventory = inventory.warehouses.map { it.toDomain() }
  )
}
