package br.com.blz.testjava.model.responses

data class ProductResponse(
  val name: String,
  val sku: Int,
  val inventory: InventoryResponse,
  val isMarketable: Boolean
  )
