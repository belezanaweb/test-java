package br.com.blz.testjava.product.model

import br.com.blz.testjava.inventory.model.Inventory

class Product(var sku: Long, val name: String, val inventory: Inventory) {
  var isMarketable: Boolean = inventory.quantity > 0
}
