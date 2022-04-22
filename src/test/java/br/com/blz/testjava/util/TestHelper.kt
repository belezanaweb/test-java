package br.com.blz.testjava.util

import br.com.blz.testjava.controller.request.InventoryRequest
import br.com.blz.testjava.controller.request.ProductRequest
import br.com.blz.testjava.controller.request.ProductUpdateRequest
import br.com.blz.testjava.controller.request.WarehouseRequest
import br.com.blz.testjava.enums.TypeWarehouse
import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.model.Warehouse


fun Product.toRequest(): ProductRequest {

  return ProductRequest(
    sku = this.sku,
    name = this.name,
    inventory = this.inventory.toRequest()
  )
}

fun Product.toUpdateRequest(): ProductUpdateRequest {

  return ProductUpdateRequest(
    name = this.name,
    inventory = this.inventory.toRequest()
  )
}

fun Inventory.toRequest(): InventoryRequest {
  return InventoryRequest(warehouses = this.warehouses.map { it.toRequest() })
}

fun Warehouse.toRequest(): WarehouseRequest {
  return WarehouseRequest(locality = this.locality, quantity = this.quantity, type = this.type)
}

fun fakeProduct(
  sku: Long,
  name: String,
  locality: String,
  quantity: Int,
  type: TypeWarehouse
): Product {
  val warehouses = Warehouse(locality, quantity, type)
  val inventory = Inventory(listOf(warehouses))

  return Product(sku, name, inventory)
}
