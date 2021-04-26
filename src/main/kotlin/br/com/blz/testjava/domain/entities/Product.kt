package br.com.blz.testjava.domain.entities

class Product(
  var sku: Long,
  var name: String,
  var inventory: Inventory
) {
  fun isMarketable() = inventory.getQuantity() > 0
}
