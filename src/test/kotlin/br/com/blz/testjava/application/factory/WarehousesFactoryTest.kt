package br.com.blz.testjava.application.factory

import br.com.blz.testjava.application.model.WarehouseModel
import br.com.blz.testjava.domain.entity.enums.WarehouseType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WarehousesFactoryTest {

    @Test
    fun `It should create a Warehouses domain from a Warehouses model`() {
        val model = WarehouseModel("Acre", 12, WarehouseType.ECOMMERCE)
        val factory = WarehousesFactory()
        val domain = factory.create(model)

        assertEquals(domain.locality, "Acre")
        assertEquals(domain.quantity, 12)
        assertEquals(domain.type.name, "ECOMMERCE")
    }

}
