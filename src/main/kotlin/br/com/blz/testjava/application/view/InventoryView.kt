package br.com.blz.testjava.application.view

data class InventoryView(
  val quantity: Int,
  val warehouses: List<WarehouseView>
)
