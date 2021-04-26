package br.com.blz.testjava.controller.dto

import br.com.blz.testjava.domain.entities.Inventory

class InventoryDTO() {
  var warehouses: List<WarehouseDTO> = listOf()
  var quantity: Int = 0

  constructor(inventory: Inventory): this() {
    quantity = inventory.getQuantity()
    warehouses = inventory.warehouses.map { warehouse -> WarehouseDTO(warehouse) }
  }

}
