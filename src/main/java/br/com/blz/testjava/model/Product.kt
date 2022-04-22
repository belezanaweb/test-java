package br.com.blz.testjava.model

import javax.persistence.Id

data class Product(
  @Id
  val sku: Long,
  val name: String,
  val inventory: Inventory,

) {

  var isMarketable: Boolean? = null
  set(_) {
    field = this.inventory.quantity?.let { it > 0}
  }
}
