package com.boticario.testkotlin.domain.entity

import com.boticario.testkotlin.factory.ProductFactory.warehousesRequest
import java.io.InvalidObjectException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class InventoryTest {
    private val warehouses = warehousesRequest.map { it.toDomain() }.toMutableList()
    private val inventory = Inventory(warehouses)

    @Test
    fun `Given Inventory instance, When warehouses parameters are input, Then calculate inventory items quantity from warehouses`() {

        assert(inventory.quantity == warehousesRequest.sumOf { it.quantity })
    }

    @Test
    fun `Given Inventory instance, When warehouses parameters are not input, Then inventory items quantity must be 0`() {
        val inventory = Inventory()

        assert(inventory.quantity == 0)
    }

    @Test
    fun `Given Inventory instance, When invalid quantity parameter is input, Then throw Exception`() {
        val result = assertThrows<InvalidObjectException> { Inventory(warehouses, 12) }

        assert(result.message == "Wrong inventory quantity!")
    }
}