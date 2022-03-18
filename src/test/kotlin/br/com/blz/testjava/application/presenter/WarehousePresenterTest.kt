package br.com.blz.testjava.application.presenter

import br.com.blz.testjava.application.model.WarehouseModel
import br.com.blz.testjava.domain.enums.Locality
import br.com.blz.testjava.domain.enums.WarehouseType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WarehousePresenterTest {

    @Test
    fun `It should create a Warehouse View from a Warehouse Model`() {
        val model = WarehouseModel(
            locality = Locality.AC,
            quantity = 21,
            type = WarehouseType.ECOMMERCE
        )
        val presenter = WarehousePresenter()
        val view = presenter.present(model)

        assertEquals(view.locality.name, "AC")
        assertEquals(view.quantity, 21)
        assertEquals(view.type.name, "ECOMMERCE")
    }

}
