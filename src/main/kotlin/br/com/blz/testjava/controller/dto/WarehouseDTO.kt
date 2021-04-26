package br.com.blz.testjava.controller.dto

import br.com.blz.testjava.domain.entities.Warehouse
import br.com.blz.testjava.domain.entities.WarehouseType

class WarehouseDTO() {
  var locality: String = ""
  var quantity: Int = 0
  var type: WarehouseType = WarehouseType.PHYSICAL_STORE

  constructor(warehouse: Warehouse): this() {
    this.locality = warehouse.locality
    this.quantity = warehouse.quantity
    this.type = warehouse.type
  }
}
