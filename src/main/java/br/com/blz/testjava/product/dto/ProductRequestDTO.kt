package br.com.blz.testjava.product.dto

import br.com.blz.testjava.types.WarehouseType

data class ProductRequestDTO(
  val sku: Long,
  val name: String,
  val inventory: ProductInventoryRequestDTO
)

data class ProductInventoryRequestDTO(
  val warehouses: List<WareHouseDTO>
)

data class WareHouseDTO(
  val locality: String,
  val quantity: Int,
  val type: WarehouseType
)

