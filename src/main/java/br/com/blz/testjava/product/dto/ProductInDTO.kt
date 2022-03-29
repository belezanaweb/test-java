package br.com.blz.testjava.product.dto

import br.com.blz.testjava.inventory.dto.InventoryInDTO

class ProductInDTO(var sku: Long, val name: String, val inventory: InventoryInDTO)
