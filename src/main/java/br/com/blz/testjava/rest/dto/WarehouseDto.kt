package br.com.blz.testjava.rest.dto

import br.com.blz.testjava.enum.ProductType
import br.com.blz.testjava.model.Warehouse

class WarehouseDto(
  var locality: String,
  var quantity: Int,
  var type: ProductType
) {

  companion object {
    fun fromWarehouseModel(warehouse: Warehouse): WarehouseDto {
      return WarehouseDto(
        warehouse.locality,
        warehouse.quantity,
        warehouse.type
      )
    }
  }

  fun toWarehouseModel(): Warehouse {
    return Warehouse(
      locality,
      quantity,
      type
    )
  }

}
