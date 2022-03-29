package br.com.blz.testjava.product.dto

import br.com.blz.testjava.inventory.dto.InventoryOutDTO

data class ProductOutDTO(val sku: Long, val name: String, val inventory: InventoryOutDTO, val isMarketable: Boolean)
