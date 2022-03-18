package br.com.blz.testjava.application.presenter

import br.com.blz.testjava.domain.entity.Warehouse
import br.com.blz.testjava.domain.entity.enums.WarehouseType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WarehousePresenterTest {

    @Test
    fun `It should create a Warehouse View from a Warehouse`() {
        val model = Warehouse(
            locality = "Acre",
            quantity = 21,
            type = WarehouseType.ECOMMERCE
        )
        val presenter = WarehousePresenter()
        val view = presenter.present(model)

        assertEquals(view.locality, "Acre")
        assertEquals(view.quantity, 21)
        assertEquals(view.type.name, "ECOMMERCE")
    }

}
