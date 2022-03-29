package br.com.blz.testjava.inventory.dto

import br.com.blz.testjava.warehouse.dto.WarehouseDTO

class InventoryOutDTO(val quantity: Long, val warehouses: List<WarehouseDTO>)
