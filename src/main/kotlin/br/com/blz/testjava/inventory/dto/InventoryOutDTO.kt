package br.com.blz.testjava.inventory.dto

import br.com.blz.testjava.warehouse.dto.WarehouseDTO

data class InventoryOutDTO(val quantity: Int, val warehouses: List<WarehouseDTO>)
