package br.com.blz.testjava.model

class Inventory(
  val warehouses: MutableList<Warehouse>
) {

  fun getQuantity():Int {
    return warehouses.map { warehouse -> warehouse.quantity }
      .reduce { productQuantity, wareHouseQuantity -> productQuantity + wareHouseQuantity }
  }
}
