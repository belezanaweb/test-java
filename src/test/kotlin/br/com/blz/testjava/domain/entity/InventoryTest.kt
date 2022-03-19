package br.com.blz.testjava.domain.entity

import br.com.blz.testjava.domain.entity.enums.WarehouseType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class InventoryTest {

    companion object {
        fun createInventory() = Inventory(mutableListOf(
            Warehouse("", 12, WarehouseType.ECOMMERCE),
            Warehouse("", 9, WarehouseType.ECOMMERCE)
        ))
    }

    @Test
    fun `It should calculate the quantity of an Inventory`() {
        val inventory = createInventory()

        assertEquals(inventory.quantity, 21)
    }

}
