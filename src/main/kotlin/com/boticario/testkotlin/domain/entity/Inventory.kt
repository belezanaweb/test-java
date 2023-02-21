package com.boticario.testkotlin.domain.entity

import java.io.InvalidObjectException

data class Inventory(
        val warehouses: MutableList<Warehouse> = mutableListOf(),
        val quantity: Int = warehouses.sumOf { it.quantity }
) {
    init {
        if(quantity != warehouses.sumOf { it.quantity })
            throw InvalidObjectException("Wrong inventory quantity!")
    }
}
