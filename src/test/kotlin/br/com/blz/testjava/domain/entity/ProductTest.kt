package br.com.blz.testjava.domain.entity

import br.com.blz.testjava.domain.entity.enums.WarehouseType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ProductTest {

    @Test
    fun `A Product with more of zero Inventory should be Marketable`() {
        val product = Product(12, "name", InventoryTest.createInventory())
        assertTrue(product.isMarketable)
    }

    @Test
    fun `A Product with zero Inventory should not be Marketable`() {
        val product = Product(12, "name", Inventory(mutableListOf(
            Warehouse("", 0, WarehouseType.ECOMMERCE))))
        assertFalse(product.isMarketable)
    }

}
