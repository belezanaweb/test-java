package br.com.blz.testjava.product.converters

import br.com.blz.testjava.product.dto.*
import br.com.blz.testjava.product.model.Product
import br.com.blz.testjava.product.model.ProductInventory
import br.com.blz.testjava.product.model.WareHouse

fun ProductRequestDTO.toEntity() = Product(
  sku = this.sku,
  name = this.name,
  inventory = inventory.toEntity(),
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
  inventory = inventory.toDTO(),
  isMarketable = defineIsMarketable(this)
)

fun ProductInventory.toDTO(): ProductInventoryResponseDTO {
  val (warehouses, total) = convertWarehouses(this.warehouses)
  return ProductInventoryResponseDTO(
    warehouses = warehouses,
    quantity = total
  )
}

/**
 * Aproveita a iteração para converter o objeto e ao mesmo tempo somar
 */
fun convertWarehouses(warehouses: List<WareHouse>): Pair<List<WareHouseResponseDTO>, Int> {
  var total = 0
  val result = mutableListOf<WareHouseResponseDTO>()
  warehouses.forEach {
    total += it.quantity
    result.add(it.toEntity())
  }
  return Pair(result, total)
}

fun WareHouse.toEntity() = WareHouseResponseDTO(
  locality = this.locality,
  quantity = this.quantity,
  type = this.type
)

/**
 * Se encontrar um Warehouse com quantity > 0 já é suficiente pra marcar
 * que isMarketable é true
 */
fun defineIsMarketable(product: Product): Boolean {
  return product.inventory.warehouses.any { it.quantity > 0 }
}
