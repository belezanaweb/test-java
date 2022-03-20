package br.com.blz.testjava.model

class Product(
    val sku:Long,
    var name:String,
    var inventory: Inventory
   ) {

  fun isMarketable():Boolean {
    return inventory.getQuantity() > 0
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Product

    if (sku != other.sku) return false

    return true
  }

  override fun hashCode(): Int {
    return sku.hashCode()
  }


}
