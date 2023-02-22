package com.boticario.testkotlin.application.request

import com.boticario.testkotlin.domain.entity.Warehouse
import com.boticario.testkotlin.domain.enum.WarehouseType

data class WarehouseRequest(
        val locality: String,
        val quantity: Int,
        val type: WarehouseType
) {
    fun toDomain() = Warehouse(locality, quantity, type)
}
