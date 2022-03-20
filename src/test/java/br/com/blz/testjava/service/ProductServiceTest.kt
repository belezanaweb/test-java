package br.com.blz.testjava.service

import br.com.blz.testjava.Exception.NotExistsException
import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import kotlin.test.assertFailsWith

class ProductServiceTest {
  private lateinit var productService: ProductService
  private lateinit var productRepositoryMock: ProductRepository
  private lateinit var product: Product

  @BeforeEach
  fun init() {
    productRepositoryMock = Mockito.mock(ProductRepository::class.java)
    productService = ProductService(productRepositoryMock)
    product = Product(12345L, "perfume", Inventory( mutableListOf()))

  }

  @Test
  fun createProduct_shouldBeTrueTest() {
    Mockito.`when`(productRepositoryMock.save(product))
      .thenReturn(true)
    Assertions.assertTrue(productService.createProduct(product))
  }

  @Test
  fun createProduct_shouldBeFalseTest() {
    Mockito.`when`(productRepositoryMock.save(product))
      .thenReturn(false)
    Assertions.assertFalse(productService.createProduct(product))
  }

  @Test
  fun updateProduct_shouldReturnProductTest() {
    Mockito.`when`(productRepositoryMock.update(product))
      .thenReturn(product)
    Assertions.assertEquals(productService.updateProduct(product), product)
  }

  @Test
  fun updateProduct_shouldThrowNotExistsException() {
    Mockito.`when`(productRepositoryMock.update(product))
      .thenThrow(NotExistsException("Product sku not found"))
    assertFailsWith(
      exceptionClass = NotExistsException::class,
      block = { productService.updateProduct(product)}
    )
  }

  @Test
  fun findBySku_shouldReturnProductTest() {
    Mockito.`when`(productRepositoryMock.findBySku(product.sku)).thenReturn(product)
    val productReturn = productService.findBySku(product.sku)
    Assertions.assertEquals(productReturn, product)
  }

  @Test
  fun findBySku_shouldThrowNotExistsExceptionTest() {
    Mockito.`when`(productRepositoryMock.findBySku(product.sku)).thenThrow(NotExistsException("Product sku not found"))
    assertFailsWith(
      exceptionClass = NotExistsException::class,
      block = { productService.findBySku(product.sku) }
    )
  }

  @Test
  fun deleteProduct_shouldBeTrueTest() {
    Mockito.`when`(productRepositoryMock.delete(product.sku)).thenReturn(true)
    Assertions.assertTrue(productService.deleteProduct(product.sku))
  }

  @Test
  fun deleteProduct_shouldThrowNotExistsExceptionTest() {
    Mockito.`when`(productRepositoryMock.delete(product.sku)).thenThrow(NotExistsException("Product sku not found"))
    assertFailsWith(
      exceptionClass = NotExistsException::class,
      block = { productService.deleteProduct(product.sku)}
    )
  }
}
