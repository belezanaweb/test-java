package br.com.blz.testjava.product.dto

import br.com.blz.testjava.inventory.dto.InventoryOutDTO

class ProductOutDTO(val sku: Long, name: String, val inventory: InventoryOutDTO, val isMarketable: Boolean)
