package br.com.blz.testjava.model

data class Product(
  val sku: Long = 0,
  val name: String = "",
  val inventory: Inventory
)
