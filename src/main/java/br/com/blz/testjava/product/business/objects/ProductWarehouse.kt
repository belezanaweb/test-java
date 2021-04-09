package br.com.blz.testjava.product.business.objects

data class ProductWarehouse(
  val locality: String,
  val quantity: Int,
  val type: WarehouseTypes
)
