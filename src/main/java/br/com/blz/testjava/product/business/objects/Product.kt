package br.com.blz.testjava.product.business.objects

data class Product(
  val sku: Long,
  val name: String,
  val inventory: ProductInventory
)
