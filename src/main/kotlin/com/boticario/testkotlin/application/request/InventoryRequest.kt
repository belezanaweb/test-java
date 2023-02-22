package com.boticario.testkotlin.application.request

import com.boticario.testkotlin.domain.entity.Inventory

data class InventoryRequest(
        val warehouses: MutableList<WarehouseRequest> = mutableListOf()
) {
    fun toDomain() = Inventory(warehouses.map { it.toDomain() }.toMutableList())
}
