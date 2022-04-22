package br.com.blz.testjava.model


data class Inventory (
  val warehouses: List<Warehouse>
) {

  var quantity: Int? = null

  set(_) {
    field = this.warehouses.sumOf { it.quantity }
  }
}
