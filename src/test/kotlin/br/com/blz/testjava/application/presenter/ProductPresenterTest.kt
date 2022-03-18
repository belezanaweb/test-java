package br.com.blz.testjava.application.presenter

import br.com.blz.testjava.domain.entity.Inventory
import br.com.blz.testjava.domain.entity.Product
import br.com.blz.testjava.domain.entity.Warehouse
import br.com.blz.testjava.domain.entity.enums.WarehouseType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class ProductPresenterTest {

    @Test
    fun `It should create a Product View from a Product`() {
        val model = Product(
            sku = 12344,
            name = "Product test",
            inventory = Inventory(warehouses = mutableListOf(Warehouse(locality = "Acre", quantity = 12, type = WarehouseType.ECOMMERCE)))
        )

        val presenter = ProductPresenter(InventoryPresenter(mock()))
        val view = presenter.present(model)

        assertEquals(view.sku, 12344)
        assertEquals(view.name, "Product test")
    }

}
