package br.com.blz.testjava.product.api.dto

import br.com.blz.testjava.product.business.objects.Product
import br.com.blz.testjava.product.business.objects.ProductInventory
import br.com.blz.testjava.product.business.objects.ProductWarehouse
import br.com.blz.testjava.product.business.objects.WarehouseTypes
import br.com.blz.testjava.product.exception.ProductValidationException

data class ProductAPISaveInputDTO(val sku: Long? = null, val name: String? = null, val inventory: ProductInventoryAPISaveInputDTO? = null) {
  fun toProduct(): Product = Product(
    sku?.let { it } ?: throw ProductValidationException("sku"),
    name?.let { it } ?: throw  ProductValidationException("name"),
    inventory = ProductInventory(
      inventory?.warehouses!!.map { ProductWarehouse(it.locality, it.quantity, WarehouseTypes.valueOf(it.type)) }
    )
  )
}

data class ProductInventoryAPISaveInputDTO(val warehouses: List<ProductWarehouseAPISaveInputDTO> = mutableListOf())

data class ProductWarehouseAPISaveInputDTO(val locality: String, val quantity: Int, val type: String)
