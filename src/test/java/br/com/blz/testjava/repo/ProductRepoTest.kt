package br.com.blz.testjava.repo

import br.com.blz.testjava.enum.TypeWarehouse
import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.ProductRequest
import br.com.blz.testjava.model.Warehouse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ProductRepoTest {

  private var products = mutableListOf<ProductRequest>()

  private var productRepo = ProductRepo(products)

  @Test
  fun `when to pick up product armazened should return product by sku`() {
    productRepo.insertProduct(createProductRequest(1, "Malbec Gold Desodorante Colônia 100ml"))
    val product = productRepo.getProduct(1)
    assertAll({
      assertEquals(1L, product.sku)
      assertEquals("Malbec Gold Desodorante Colônia 100ml", product.name)
      assertEquals("Ecommerce", product.inventory.warehouses[0].locality)
      assertEquals(12, product.inventory.warehouses[0].quantity)
      assertEquals(TypeWarehouse.ECOMMERCE, product.inventory.warehouses[0].type)
    })
  }

  @Test
  fun `when to pick up product not armazened should throw exception`() {
    assertThrows<Exception>{
      productRepo.getProduct(12)
    }
  }

  @Test
  fun `when delete product armazened should return sku number`() {
    productRepo.insertProduct(createProductRequest(1, "Malbec Gold Desodorante Colônia 100ml"))
    val sku = productRepo.deleteProduct(1)
    assertEquals(1, sku)
  }

  @Test
  fun `when delete product not armazened should throw exception`() {
    assertThrows<Exception>{
      productRepo.deleteProduct(12)
    }
  }

  @Test
  fun `when update product armazened should return product updated`() {
    productRepo.insertProduct(createProductRequest(1, "Malbec Gold Desodorante Colônia 100ml"))
    val product = createProductRequest(1, "Florata 75ml")
    val productUpdated = productRepo.updateProduct(product)
    assertAll({
      assertEquals(1L, productUpdated.sku)
      assertEquals("Florata 75ml", productUpdated.name)
      assertEquals("Ecommerce", productUpdated.inventory.warehouses[0].locality)
      assertEquals(12, productUpdated.inventory.warehouses[0].quantity)
      assertEquals(TypeWarehouse.ECOMMERCE, productUpdated.inventory.warehouses[0].type)
    })
  }

  @Test
  fun `when to update product not armazened should throw exception`() {
    val product = createProductRequest(1, "Florata 75ml")
    assertThrows<Exception>{
      productRepo.updateProduct(product)
    }
  }

  private fun createProductRequest(sku: Long, name: String): ProductRequest {
    val ecommerce = Warehouse(
      "Ecommerce",
      12,
      TypeWarehouse.ECOMMERCE
    )
    val inventory = Inventory(
      listOf(ecommerce)
    )
    return ProductRequest(
      sku,
      name,
      inventory
    )
  }
}
