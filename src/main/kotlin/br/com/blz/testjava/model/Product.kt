package br.com.blz.testjava.model

data class Product(
  var sku: Long? = null,
  val name: String,
  val inventory: Inventory
){
val isMarketable: Boolean
  get() = inventory.quantity > 0
}
