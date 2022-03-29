package br.com.blz.testjava.product.model

import br.com.blz.testjava.inventory.provider.InventoryProvider
import br.com.blz.testjava.product.service.provider.ProductProvider
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class ProductTest {
  @Test
  fun `should return isMarketable true when inventory has quantity`() {
    val product = ProductProvider.createEntity(InventoryProvider.createEntityWithATotalOf15WareHouse())
    assertTrue(product.isMarketable)
  }

  @Test
  fun `should return isMarketable false when inventory has no quantity`() {
    val product = ProductProvider.createEntity(InventoryProvider.createEntityWithoutWareHouse())
    assertFalse(product.isMarketable)
  }
}
