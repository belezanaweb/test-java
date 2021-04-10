package br.com.blz.testjava.product

import br.com.blz.testjava.product.api.dto.ProductAPISaveInputDTO
import br.com.blz.testjava.product.api.dto.ProductInventoryAPISaveInputDTO
import br.com.blz.testjava.product.api.dto.ProductWarehouseAPISaveInputDTO
import br.com.blz.testjava.product.business.objects.WarehouseTypes

object ProductTestTemplates {
  fun createProductInputDTO(sku: Long = 1L, name: String = "Product 1", inventory: ProductInventoryAPISaveInputDTO = createProductInventory()): ProductAPISaveInputDTO = ProductAPISaveInputDTO(sku, name, inventory)
  fun createProductInventory(warehouses: List<ProductWarehouseAPISaveInputDTO> = listOf()) : ProductInventoryAPISaveInputDTO = ProductInventoryAPISaveInputDTO(warehouses)
  fun createProductWarehouse(locality: String = "SP", quantity: Int = 1, type: WarehouseTypes) = ProductWarehouseAPISaveInputDTO(locality, quantity, type.toString())
}
