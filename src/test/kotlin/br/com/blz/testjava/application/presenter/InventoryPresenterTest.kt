package br.com.blz.testjava.application.presenter

import br.com.blz.testjava.domain.entity.Inventory
import br.com.blz.testjava.domain.entity.Warehouse
import br.com.blz.testjava.domain.entity.enums.WarehouseType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class InventoryPresenterTest {

    @Test
    fun `It should create a Inventory View from a Inventory`() {
        val model = Inventory(
            warehouses = mutableListOf(
                Warehouse(locality = "Acre", quantity = 12, type = WarehouseType.ECOMMERCE)
            )
        )

        val presenter = InventoryPresenter(mock())
        val view = presenter.present(model)

        assertEquals(view.quantity, 12)
    }

}
