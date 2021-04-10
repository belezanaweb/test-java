package br.com.blz.testjava.product

import br.com.blz.testjava.product.api.dto.ProductAPISaveInputDTO
import br.com.blz.testjava.product.api.dto.ProductInventoryAPISaveInputDTO
import br.com.blz.testjava.product.api.dto.ProductWarehouseAPISaveInputDTO
import br.com.blz.testjava.product.business.objects.Product
import br.com.blz.testjava.product.business.objects.ProductInventory
import br.com.blz.testjava.product.business.objects.ProductWarehouse
import br.com.blz.testjava.product.business.objects.WarehouseTypes

object ProductTestTemplates {
  fun createProductInputDTO(sku: Long = 1L, name: String = "Product 1", inventory: ProductInventoryAPISaveInputDTO = createProductInputInventory()): ProductAPISaveInputDTO = ProductAPISaveInputDTO(sku, name, inventory)
  fun createProductInputInventory(warehouses: List<ProductWarehouseAPISaveInputDTO> = listOf()) : ProductInventoryAPISaveInputDTO = ProductInventoryAPISaveInputDTO(warehouses)
  fun createProductInputWarehouse(locality: String = "SP", quantity: Int = 1, type: WarehouseTypes) = ProductWarehouseAPISaveInputDTO(locality, quantity, type.toString())

  fun createProduct(sku: Long = 1L, name: String = "Product 1", inventory: ProductInventory = createProductInventory()) : Product = Product(sku, name, inventory)
  fun createProductInventory(warehouses: List<ProductWarehouse> = listOf(createProductWarehouse())): ProductInventory = ProductInventory(warehouses)
  fun createProductWarehouse(locality: String = "SP", quantity: Int = 1, type: WarehouseTypes = WarehouseTypes.ECOMMERCE) : ProductWarehouse = ProductWarehouse(locality, quantity, type)
}
