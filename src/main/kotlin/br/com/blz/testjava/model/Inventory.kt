package br.com.blz.testjava.model

data class Inventory(
  val quantity: Int = 0,
  val warehouses: List<Warehouse>
)
