package br.com.blz.testjava.inventory.model

import br.com.blz.testjava.inventory.provider.InventoryProvider
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class InventoryTest {

  @Test
  fun `must calculate amount correctly`() {
    val inventory = InventoryProvider.createEntityWithATotalOf15WareHouse()
    assertEquals(15, inventory.quantity)
  }
}
