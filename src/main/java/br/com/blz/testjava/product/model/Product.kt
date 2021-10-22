package br.com.blz.testjava.product.model

import br.com.blz.testjava.types.WarehouseType

data class Product(
  val sku: Long,
  val name: String,
  val inventory: ProductInventory
)

data class ProductInventory(
  val warehouses: List<WareHouse>
)

data class WareHouse(
  val locality: String,
  val quantity: Int,
  val type: WarehouseType
)

