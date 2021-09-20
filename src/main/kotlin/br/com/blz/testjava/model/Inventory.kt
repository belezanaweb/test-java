package br.com.blz.testjava.model

data class Inventory(
  val warehouses: List<Warehouse> = listOf()
){
  val quantity: Long
    get() = warehouses.stream().mapToLong(Warehouse::quantity).sum()
}
