package br.com.blz.testjava.controller.request

import javax.validation.Valid
import javax.validation.constraints.NotEmpty

data class InventoryRequest(

  @field:Valid
  @field:NotEmpty
  val warehouses: List<WarehouseRequest>
)
