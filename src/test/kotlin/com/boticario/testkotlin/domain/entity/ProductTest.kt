package com.boticario.testkotlin.domain.entity

import com.boticario.testkotlin.factory.ProductFactory.createProduct
import java.io.InvalidObjectException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ProductTest {
    private val product = createProduct

    @Test
    fun `Given product instance, When valid parameters are input, Then set isMarketable to true`() {
        assert(product.isMarketable)
    }

    @Test
    fun `Given product instance, When only sku and name are input, Then set isMarketable to false`() {

        assert(!Product(1234, "test").isMarketable)
    }

    @Test
    fun `Given product instance, When invalid isMarketable parameter is input, Then throw Exception`() {
        val result = assertThrows<InvalidObjectException> { Product(1234, "test", createProduct.inventory, false) }
        val result2 = assertThrows<InvalidObjectException> { Product(sku = 1234, name = "test", isMarketable = true) }

        assert(result.message == "Wrong isMarketable flag for product, invalid data!")
        assert(result2.message == "Wrong isMarketable flag for product, invalid data!")
    }
}