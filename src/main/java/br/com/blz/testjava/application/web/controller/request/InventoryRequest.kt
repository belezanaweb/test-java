package br.com.blz.testjava.application.web.controller.request

data class InventoryRequest(
  val quantity: Int,
  val warehouses: List<WarehouseRequest>
)
