package br.com.blz.testjava.repository

import br.com.blz.testjava.Exception.NotExistsException
import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.Product
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class ProductInMemoryRepositoryTest {
  private lateinit var productInMemoryRepository: ProductInMemoryRepository
  private lateinit var product: Product

  @BeforeEach
  fun init() {
    productInMemoryRepository = ProductInMemoryRepository()
    product = Product(12345L, "perfume", Inventory(mutableListOf()))
  }

  @Test
  fun saveProduct_shouldBeTrueTest() {
    val isSaved = productInMemoryRepository.save(product)
    Assertions.assertTrue(isSaved)
  }

  @Test
  fun saveProduct_shouldBeFalseTest() {
    productInMemoryRepository.save(product)
    val isSaved = productInMemoryRepository.save(product)
    Assertions.assertFalse(isSaved)
  }

  @Test
  fun updateProduct_shouldUpdateProductTest() {
    productInMemoryRepository.save(product)
    product.name = "creme"
    val uodatedProduct = productInMemoryRepository.update(product)
    Assertions.assertNotNull(uodatedProduct)
  }

  @Test
  fun updateProduct_shouldThrowNotExistsExceptionTest() {
    productInMemoryRepository.save(product)
    val product2 = Product(54321L, "creme", Inventory(mutableListOf()))
    assertFailsWith(
      exceptionClass = NotExistsException::class,
      block = { productInMemoryRepository.update(product2) })
  }

  @Test
  fun findProduct_shouldBeFoundTest() {
    productInMemoryRepository.save(product)
    val product = productInMemoryRepository.findBySku(product.sku)
    Assertions.assertNotNull(product)
  }

  @Test
  fun findProduct_shouldThrowNotExistsException() {
    assertFailsWith(
      exceptionClass = NotExistsException::class,
      block = { productInMemoryRepository.findBySku(product.sku) }
    )
  }

  @Test
  fun deleteProduct_shouldBeTrueTest() {
    productInMemoryRepository.save(product)
    Assertions.assertTrue(productInMemoryRepository.delete(product.sku))
  }

  @Test
  fun deleteProduct_sshouldThrowNotExistsException() {
    assertFailsWith(
      exceptionClass = NotExistsException::class,
      block = { productInMemoryRepository.delete(product.sku)}
    )
  }
}
