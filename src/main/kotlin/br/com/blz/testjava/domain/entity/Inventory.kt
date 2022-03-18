package br.com.blz.testjava.domain.entity

class Inventory(
    val warehouses: MutableList<Warehouse>
) {

    var quantity: Int = 0
      get() = warehouses.sumOf { it.quantity }

}
