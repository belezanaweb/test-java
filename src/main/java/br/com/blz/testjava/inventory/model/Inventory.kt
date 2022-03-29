package br.com.blz.testjava.inventory.model

import br.com.blz.testjava.warehouse.model.Warehouse

class Inventory(val warehouses: List<Warehouse>) {
  val quantity: Int = warehouses.sumOf { it.quantity }
}
