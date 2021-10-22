package br.com.blz.testjava.product.dto

import br.com.blz.testjava.types.WarehouseType

data class ProductResponseDTO(
  val sku: Long,
  val name: String,
  val inventory: ProductInventoryResponseDTO
)

data class ProductInventoryResponseDTO(
  val warehouses: List<WareHouseResponseDTO>
)

data class WareHouseResponseDTO(
  val locality: String,
  val quantity: Int,
  val type: WarehouseType
)

