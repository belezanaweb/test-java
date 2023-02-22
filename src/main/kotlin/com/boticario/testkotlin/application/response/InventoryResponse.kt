package com.boticario.testkotlin.application.response

import com.boticario.testkotlin.domain.entity.Inventory

data class InventoryResponse(
        val warehouses: MutableList<WarehouseResponse>,
        val quantity: Int
) {
  companion object {
    fun fromDomain(inventory: Inventory) = InventoryResponse(
      inventory.warehouses.map { WarehouseResponse.fromDomain(it) }.toMutableList(),
      inventory.quantity
    )
  }
}
