package br.com.blz.testjava.domain.model

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ProductTest {

    @Test
    fun `Deve permitir saber se um produto e comercializavel`() {
        assertFalse(Product(1, "L'Oréal Professionnel", mockInventory(0)).isMarketable())
        assertTrue (Product(1, "L'Oréal Professionnel", mockInventory(1)).isMarketable())
    }

    private fun mockInventory(quantity: Int) =
        `when`(mock(Inventory::class.java).calculateTotalQuantity()).thenReturn(quantity).getMock<Inventory>()

}