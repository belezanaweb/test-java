package br.com.blz.testjava.model

import br.com.blz.testjava.enum.TypeWarehouse

data class ProductRequest(
  val sku: Long,
  val name: String,
  val inventory: Inventory
)
