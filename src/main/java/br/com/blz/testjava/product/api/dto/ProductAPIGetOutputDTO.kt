package br.com.blz.testjava.product.api.dto

import br.com.blz.testjava.product.business.objects.Product
import br.com.blz.testjava.product.business.objects.ProductInventory
import br.com.blz.testjava.product.business.objects.ProductWarehouse

data class ProductAPIGetOutputDTO (
  val sku: Long? = null,
  val name: String? = null,
  val inventory: ProductInventoryAPIGetOutputDTO? = null,
  var isMarketable: Boolean? = null
) {
  constructor(product: Product) : this(
    product.sku,
    product.name,
    ProductInventoryAPIGetOutputDTO(product.inventory),
    product.inventory.warehouses.any { it.quantity > 0 }
  )
}

data class ProductInventoryAPIGetOutputDTO(
  val quantity: Int? = null,
  val warehouses: List<ProductWarehouseAPIGetOutputDTO> = listOf()
) {
  constructor(productInventory: ProductInventory) : this(
    productInventory.warehouses.sumBy { it.quantity },
    productInventory.warehouses.map { ProductWarehouseAPIGetOutputDTO(it) }
  )
}

data class ProductWarehouseAPIGetOutputDTO(
  val locality: String? = null,
  val quantity: Int? = null,
  val type: String? = null
) {
  constructor(productWarehouse: ProductWarehouse) : this(productWarehouse.locality, productWarehouse.quantity, productWarehouse.type.toString())
}
