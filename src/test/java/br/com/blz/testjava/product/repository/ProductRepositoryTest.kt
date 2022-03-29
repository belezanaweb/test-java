package br.com.blz.testjava.product.repository

import br.com.blz.testjava.product.service.provider.ProductProvider
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class ProductRepositoryTest {

  @InjectMocks
  lateinit var productRepository: ProductRepository

  @BeforeEach
  private fun setUp() {
    productRepository.memoryDataBase = hashMapOf()
  }

  @Test
  fun `must save product correctly`() {
    val product = ProductProvider.createEntity()

    val savedProduct = productRepository.save(product)

    assertEquals(product, savedProduct)
    assertEquals(product, productRepository.findBySku(product.sku).get())
  }

  @Test
  fun `should return false when there is no registered product`() {
    assertFalse(productRepository.existsBySku(43264L))
  }

  @Test
  fun `must return true when there is a registered product`() {
    val product = ProductProvider.createEntity()

    productRepository.save(product)

    assertTrue(productRepository.existsBySku(product.sku))
  }

  @Test
  fun `must return product by sku correctly`() {
    val product = ProductProvider.createEntity()

    productRepository.save(product)
    productRepository.findBySku(product.sku)

    assertTrue(productRepository.existsBySku(product.sku))
  }

  @Test
  fun `must delete product by sku correctly`() {
    val product = ProductProvider.createEntity()

    productRepository.save(product)
    productRepository.deleteBySku(product.sku)

    assertFalse(productRepository.existsBySku(product.sku))
  }

}
