package br.com.blz.testjava.converter

import br.com.blz.testjava.enum.TypeWarehouse
import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.ProductRequest
import br.com.blz.testjava.model.Warehouse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ProductResquestToResponseConverterTest {

  @InjectMocks
  private lateinit var productResquestToResponseConverter: ProductResquestToResponseConverter

  @Test
  fun `should return Product response`() {
    val productRequest = createProductRequest()
    val productResponse = productResquestToResponseConverter.build(productRequest)
    assertAll({
      assertEquals(1L, productResponse.sku)
      assertEquals("Florata 75ml", productResponse.name)
      assertEquals("Ecommerce", productResponse.inventory.warehouses[0].locality)
      assertEquals(12, productResponse.inventory.warehouses[0].quantity)
      assertEquals(TypeWarehouse.ECOMMERCE, productResponse.inventory.warehouses[0].type)
      assertEquals("SP", productResponse.inventory.warehouses[1].locality)
      assertEquals(3, productResponse.inventory.warehouses[1].quantity)
      assertEquals(TypeWarehouse.PHYSICAL_STORE, productResponse.inventory.warehouses[1].type)
      assertEquals(true, productResponse.isMarketable)
      assertEquals(15, productResponse.inventory.quantity)
    })
  }

  fun createProductRequest(): ProductRequest {
    val ecommerce = Warehouse(
      "Ecommerce",
      12,
      TypeWarehouse.ECOMMERCE
    )
    val physical = Warehouse(
      "SP",
      3,
      TypeWarehouse.PHYSICAL_STORE
    )
    val inventory = Inventory(
      listOf(ecommerce, physical)
    )
    return ProductRequest(
      1,
      "Florata 75ml",
      inventory
    )
  }
}
