package br.com.blz.testjava.product.dto

import br.com.blz.testjava.types.WarehouseType

data class ProductResponseDTO(
  val sku: Long,
  val name: String,
  val inventory: ProductInventoryResponseDTO,
  val isMarketable: Boolean
)

data class ProductInventoryResponseDTO(
  val quantity: Int,
  val warehouses: List<WareHouseResponseDTO>
)

data class WareHouseResponseDTO(
  val locality: String,
  val quantity: Int,
  val type: WarehouseType
)

