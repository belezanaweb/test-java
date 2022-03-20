package br.com.blz.testjava.rest.dto

import br.com.blz.testjava.model.Inventory

class InventoryDto(
  var warehouses: MutableList<WarehouseDto>
) {

  constructor(warehouses: MutableList<WarehouseDto>,
              quantity: Int) : this(warehouses) {
    this.quantity = quantity
  }

  var quantity = 0

  companion object {
    fun fromInventoryModel(inventory: Inventory): InventoryDto {
      val inventoryDto = InventoryDto(
        mutableListOf()
      )
      inventoryDto.quantity = inventory.getQuantity()
      inventory.warehouses.forEach { warehouse -> inventoryDto.warehouses.add(WarehouseDto.fromWarehouseModel(warehouse)) }
      return inventoryDto
    }
  }

  fun toInventoryModel(): Inventory {
    val wareHousesModel = warehouses.map(WarehouseDto::toWarehouseModel)
    return Inventory(
      wareHousesModel.toMutableList()
    )
  }
}
