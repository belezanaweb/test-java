package br.com.blz.testjava.model

data class Inventory(
  val warehouses: List<Warehouse>,
  var quantity: Int? = null
)
