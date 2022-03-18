package br.com.blz.testjava.application.presenter

import br.com.blz.testjava.application.model.InventoryModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class InventoryPresenterTest {

    @Test
    fun `It should create a Inventory View from a Inventory Model`() {
        val model = InventoryModel(
            quantity = 10,
            isMarketable = true,
            warehouses = mock()
        )
        val presenter = InventoryPresenter(mock())
        val view = presenter.present(model)

        assertEquals(view.quantity, 10)
        assertTrue(view.isMarketable)
    }

}
