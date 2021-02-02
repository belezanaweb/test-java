package br.com.blz.testjava.service

import br.com.blz.testjava.exception.ProductAlreadyExistsException
import br.com.blz.testjava.exception.ProductNotFoundException
import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.model.Warehouses
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.math.roundToInt
import kotlin.test.assertEquals

internal class ProductServiceTest {

  private val service = ProductService()

  @Test
  fun find() {
    val product = buildProduct()
    service.save(product)
    val found = service.find(product.sku)
    assertEquals(product, found)
  }

  @Test
  fun findFail() {
    val product = buildProduct()
    assertThrows<ProductNotFoundException> { service.find(product.sku) }
  }

  @Test
  fun save() {
    val product = buildProduct()
    val save = service.save(product)
    assertEquals(product, save)
  }

  @Test
  fun saveDuplicate() {
    val product = buildProduct()
    service.save(product)
    assertThrows<ProductAlreadyExistsException> { service.save(product) }
  }

  @Test
  fun update() {
    val product = buildProduct()
    service.save(product)
    assertDoesNotThrow {
      service.update(product)
    }
  }

  @Test
  fun updateFail() {
    val product = buildProduct()
    product.sku = Math.random().roundToInt() * 10
    assertThrows<ProductNotFoundException> {
      service.update(product)
    }
  }

  @Test
  fun removeFail() {
    val product = buildProduct()
    assertThrows<ProductNotFoundException> {
      service.remove(product.sku)
    }
  }

  @Test
  fun removeSuccess() {
    val product = buildProduct()
    service.save(product)
    assertDoesNotThrow {
      service.remove(product.sku)
    }
  }

  private fun buildProduct(): Product {
    val warehouses = listOf(Warehouses("Anywhere", 11, "1"))
    return Product(3123, "Rice", Inventory(13, warehouses), null)
  }
}
