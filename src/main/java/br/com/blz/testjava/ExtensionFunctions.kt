package br.com.blz.testjava

import br.com.blz.testjava.controller.request.InventoryRequest
import br.com.blz.testjava.controller.request.ProductRequest
import br.com.blz.testjava.controller.request.ProductUpdateRequest
import br.com.blz.testjava.controller.request.WarehouseRequest
import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.model.Warehouse

fun ProductRequest.toModel(): Product {
  return Product(
    sku = this.sku,
    name = this.name,
    inventory = this.inventory.toModel()
  )
}

fun ProductUpdateRequest.toModel(previous: Product): Product {
  return Product(
    sku = previous.sku,
    name = this.name?:previous.name,
    inventory = this.inventory?.toModel()?:previous.inventory
  )
}

fun InventoryRequest.toModel(): Inventory {
  return Inventory(
    warehouses = this.warehouses.map { it.toModel() }
  )
}

fun WarehouseRequest.toModel(): Warehouse {
  return Warehouse(
    locality = this.locality,
    quantity = this.quantity,
    type = this.type
  )
}
