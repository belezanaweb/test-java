package br.com.blz.testjava.controller.dto

import br.com.blz.testjava.domain.entities.Inventory
import br.com.blz.testjava.domain.entities.Product
import br.com.blz.testjava.domain.entities.Warehouse

class ProductDTO() {
  var sku: Long? = null
  var name: String = ""
  var inventory: InventoryDTO = InventoryDTO()
  var isMarketable: Boolean = false

  constructor(product: Product): this() {
    this.sku = product.sku
    this.name = product.name
    this.inventory = InventoryDTO(product.inventory)
    this.isMarketable = product.isMarketable()
  }

  fun toEntity(): Product {
    return Product(sku, name,
             Inventory(inventory.warehouses
                    .map { warehouseDTO ->
                          Warehouse(warehouseDTO.locality, warehouseDTO.quantity, warehouseDTO.type)
                      })
           )
  }
}
