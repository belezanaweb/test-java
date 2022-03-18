package br.com.blz.testjava.application.presenter

import br.com.blz.testjava.application.model.InventoryModel
import br.com.blz.testjava.application.model.ProductModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class ProductPresenterTest {

    @Test
    fun `It should create a Product View from a Product Model`() {
        val model = ProductModel(
            sku = 12344,
            name = "Product test",
            inventory = InventoryModel(quantity = 12, warehouses = mock()),
            isMarketable = true
        )

        val presenter = ProductPresenter(InventoryPresenter(mock()))
        val view = presenter.present(model)

        assertEquals(view.sku, 12344)
        assertEquals(view.name, "Product test")
        assertTrue(view.isMarketable)
    }

}
