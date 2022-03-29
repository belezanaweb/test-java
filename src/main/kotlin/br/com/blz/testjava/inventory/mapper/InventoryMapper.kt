package br.com.blz.testjava.inventory.mapper

import br.com.blz.testjava.inventory.dto.InventoryInDTO
import br.com.blz.testjava.inventory.dto.InventoryOutDTO
import br.com.blz.testjava.inventory.model.Inventory
import br.com.blz.testjava.warehouse.mapper.WarehouseMapper

class InventoryMapper {
  companion object {

    fun toEntity(inventoryInDTO: InventoryInDTO): Inventory {
      return Inventory(inventoryInDTO.warehouses.map { WarehouseMapper.toEntity(it) })
    }

    fun toOutDto(inventory: Inventory): InventoryOutDTO {
      return InventoryOutDTO(
        inventory.quantity,
        inventory.warehouses.map { WarehouseMapper.toDto(it) })
    }
  }
}
