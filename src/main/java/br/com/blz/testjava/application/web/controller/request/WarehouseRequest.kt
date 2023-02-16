package br.com.blz.testjava.application.web.controller.request

import br.com.blz.testjava.domain.entity.Warehouse
import br.com.blz.testjava.domain.entity.WarehouseType

data class WarehouseRequest(
  val locality: String,
  val quantity: Int,
  val type: String
) {
  fun toDomain() = Warehouse(
    locality = locality,
    quantity = quantity,
    type = WarehouseType.valueOf(type)
  )

  companion object {
    fun fromDomain(warehouse: Warehouse) = WarehouseRequest(
      locality = warehouse.locality,
      quantity = warehouse.quantity,
      type = warehouse.type.name
    )
  }
}
