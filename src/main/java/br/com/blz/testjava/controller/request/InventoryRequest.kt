package br.com.blz.testjava.controller.request

import javax.validation.Valid

data class InventoryRequest(

  @field:Valid
  val warehouses: List<WarehouseRequest>
)
