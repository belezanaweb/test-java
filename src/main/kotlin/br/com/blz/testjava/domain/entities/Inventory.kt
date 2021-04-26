package br.com.blz.testjava.domain.entities

data class Inventory (
  var warehouses: List<Warehouse>
) {
  fun getQuantity() =
    warehouses.map { warehouse -> warehouse.quantity }
              .reduce { sum, quantity -> sum + quantity }

}
