package br.com.blz.testjava.inventory.provider

import br.com.blz.testjava.inventory.dto.InventoryInDTO
import br.com.blz.testjava.inventory.model.Inventory
import br.com.blz.testjava.warehouse.dto.WarehouseDTO
import br.com.blz.testjava.warehouse.model.Warehouse
import br.com.blz.testjava.warehouse.model.WarehouseTypeEnum

class InventoryProvider {
  companion object {
    fun createInDto(): InventoryInDTO {
      return InventoryInDTO(
        listOf(
          WarehouseDTO("SP", 12, WarehouseTypeEnum.ECOMMERCE),
          WarehouseDTO("MOEMA", 3, WarehouseTypeEnum.PHYSICAL_STORE)
        )
      )
    }

    fun createEntityWithATotalOf15WareHouse(): Inventory {
      return Inventory(
        listOf(
          Warehouse("SP", 12, WarehouseTypeEnum.ECOMMERCE),
          Warehouse("MOEMA", 3, WarehouseTypeEnum.PHYSICAL_STORE)
        )
      )
    }

    fun createEntityWithoutWareHouse(): Inventory {
      return Inventory(listOf())
    }
  }
}
