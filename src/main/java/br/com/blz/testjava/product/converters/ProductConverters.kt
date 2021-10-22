package br.com.blz.testjava.product.converters

import br.com.blz.testjava.product.dto.ProductInventoryRequestDTO
import br.com.blz.testjava.product.dto.ProductRequestDTO
import br.com.blz.testjava.product.dto.WareHouseDTO
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

fun WareHouseDTO.toEntity() = WareHouse(
  locality = this.locality,
  quantity = this.quantity,
  type = this.type
)
