package br.com.blz.testkotlin.entity

class ProductEntity(
  var sku: Long,
  var name: String,
  var inventory: InventoryEntity,
  var isMarketable: Boolean
)
