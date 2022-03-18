package br.com.blz.testjava.application.factory

import br.com.blz.testjava.application.model.InventoryModel
import br.com.blz.testjava.application.model.WarehouseModel
import br.com.blz.testjava.domain.entity.enums.WarehouseType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class InventoryFactoryTest  {

    @Test
    fun `It should create a Inventory domain from a Inventory Model`() {
        val model = InventoryModel(quantity = 12, mutableListOf(WarehouseModel("Acre", 12, WarehouseType.ECOMMERCE)))
        val factory = InventoryFactory(WarehousesFactory())
        val domain = factory.create(model)

        assertNotNull(domain.warehouses)
        assertEquals(domain.quantity, 12)
    }

}
