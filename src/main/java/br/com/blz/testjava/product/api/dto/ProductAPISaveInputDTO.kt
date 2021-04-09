package br.com.blz.testjava.product.api.dto

import br.com.blz.testjava.product.business.objects.Product
import br.com.blz.testjava.product.business.objects.ProductInventory
import br.com.blz.testjava.product.business.objects.ProductWarehouse
import br.com.blz.testjava.product.business.objects.WarehouseTypes

class ProductAPISaveInputDTO{
  var sku: Long = 0
  lateinit var name: String
  lateinit var inventory: ProductInventoryAPISaveInputDTO

  constructor()

  constructor(sku: Long, name: String, inventory: ProductInventoryAPISaveInputDTO) {
    this.sku = sku
    this.name = name
    this.inventory = inventory
  }
  fun toProduct(): Product = Product(
    sku = sku,
    name = name,
    inventory = ProductInventory(
      warehouses = inventory.warehouses.map { ProductWarehouse(it.locality, it.quantity, WarehouseTypes.valueOf(it.type)) }
    )
  )
}

class ProductInventoryAPISaveInputDTO {
  var warehouses: List<ProductWarehouseAPISaveInputDTO> = listOf()

  constructor()

  constructor(warehouses: List<ProductWarehouseAPISaveInputDTO>) {
    this.warehouses = warehouses
  }

}

class ProductWarehouseAPISaveInputDTO {
  lateinit var locality: String
  var quantity: Int = 0
  lateinit var type: String

  constructor()

  constructor(locality: String, quantity: Int, type: String) {
    this.locality = locality
    this.quantity = quantity
    this.type = type
  }


}
