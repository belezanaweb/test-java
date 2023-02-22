package com.boticario.testkotlin.domain.entity

import java.io.InvalidObjectException

data class Product(
        val sku: Long,
        val name: String,
        val inventory: Inventory = Inventory(),
        val isMarketable: Boolean = inventory.quantity > 0
) {
    init {
        if (inventory.quantity <= 0) {
            if (isMarketable)
                throw InvalidObjectException("Wrong isMarketable flag for product, invalid data!")
        }
        else if (!isMarketable)
            throw InvalidObjectException("Wrong isMarketable flag for product, invalid data!")
    }
}
