package br.com.blz.testjava.product.converters

import br.com.blz.testjava.product.dto.*
import br.com.blz.testjava.product.model.Product
import br.com.blz.testjava.product.model.ProductInventory
import br.com.blz.testjava.product.model.WareHouse

fun ProductRequestDTO.toEntity() = Product(
  sku = this.sku,
  name = this.name,
  inventory = inventory.toEntity()
)

fun ProductInventoryRequestDTO.toEntity() = ProductInventory(
  warehouses = this.warehouses.map { it.toEntity() }
)

fun WareHouseRequestDTO.toEntity() = WareHouse(
  locality = this.locality,
  quantity = this.quantity,
  type = this.type
)


fun Product.toDTO() = ProductResponseDTO(
  sku = this.sku,
  name = this.name,
  inventory = inventory.toDTO()
)

fun ProductInventory.toDTO() = ProductInventoryResponseDTO(
  warehouses = this.warehouses.map { it.toEntity() }
)

fun WareHouse.toEntity() = WareHouseResponseDTO(
  locality = this.locality,
  quantity = this.quantity,
  type = this.type
)
