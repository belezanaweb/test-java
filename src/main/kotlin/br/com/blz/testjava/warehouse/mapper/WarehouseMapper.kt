package br.com.blz.testjava.warehouse.mapper

import br.com.blz.testjava.warehouse.dto.WarehouseDTO
import br.com.blz.testjava.warehouse.model.Warehouse

class WarehouseMapper {
  companion object {

    fun toEntity(warehouseDTO: WarehouseDTO): Warehouse {
      return Warehouse(warehouseDTO.locality, warehouseDTO.quantity, warehouseDTO.type)
    }

    fun toDto(warehouse: Warehouse): WarehouseDTO {
      return WarehouseDTO(warehouse.locality, warehouse.quantity, warehouse.type)
    }
  }
}
