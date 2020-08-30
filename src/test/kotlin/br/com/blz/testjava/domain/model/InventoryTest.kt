package br.com.blz.testjava.domain.model

import br.com.blz.testjava.domain.model.WarehouseType.ECOMMERCE
import br.com.blz.testjava.domain.model.WarehouseType.PHYSICAL_STORE
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InventoryTest {

    @Test
    fun `Deve permtir calcular a quantidade total de produtos nos armazens`() {

        val warehouses = mutableListOf(Warehouse(1, 1, ECOMMERCE), Warehouse(2, 2, PHYSICAL_STORE))

        assertEquals(0, Inventory(1, mutableListOf()).calculateTotalQuantity())
        assertEquals(3, Inventory(2, warehouses).calculateTotalQuantity())

    }

}