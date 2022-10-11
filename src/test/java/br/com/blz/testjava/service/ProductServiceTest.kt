package br.com.blz.testjava.service

import br.com.blz.testjava.ProductTestData
import br.com.blz.testjava.exception.ProductAlreadyExistsException
import br.com.blz.testjava.exception.ProductNotFoundException
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.repository.ProductRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class ProductServiceTest {

  @MockK
  private lateinit var productRepository: ProductRepository

  @InjectMockKs
  private lateinit var productService: ProductService

  @BeforeEach
  fun setUp() {
    unmockkAll()
    MockKAnnotations.init(this)
  }


  @Test
  fun should_not_get_product_not_found() {
    val sku: Long = 43264

    ProductTestData.productNotMarketable()

    every { productRepository.get(sku) } returns null

    assertThrows<ProductNotFoundException> { productService.getProductBySku(sku) }
  }

  @Test
  fun should_mark_product_as_not_marketable_and_0_inventory_quantity() {
    val sku: Long = 43264

    val productNotMarketable = ProductTestData.productNotMarketable()

    every { productRepository.get(sku) } returns productNotMarketable

    val response = productService.getProductBySku(sku)
    assertFalse(response.isMarketable())
    assertEquals(0, response.inventory.quantity)
  }

  @Test
  fun should_mark_product_as_marketable_and_with_15_inventories() {
    val sku: Long = 43264

    val product = ProductTestData.productMarketable()

    every { productRepository.get(sku) } returns product

    val response = productService.getProductBySku(sku)
    assertTrue(response.isMarketable())
    assertEquals(15, response.inventory.quantity)
  }

  @Test
  fun should_create_new_product_sucessfully() {
    val sku: Long = 43264

    val product: Product = ProductTestData.productMarketable()!!

    every { productRepository.get(sku) } returns null
    every { productRepository.create(any()) } returns product

    productService.createProduct(product)

    verify(exactly = 1) { productRepository.create(product) }
  }


  @Test
  fun should_not_create_new_product_because_already_exists() {
    val sku: Long = 43264

    val product: Product = ProductTestData.productMarketable()!!

    every { productRepository.get(sku) } returns mockk()

    assertThrows<ProductAlreadyExistsException> { productService.createProduct(product) }

    verify(exactly = 0) { productRepository.create(product) }
  }

  @Test
  fun should_update_product() {

    val sku: Long = 43264

    val product: Product = ProductTestData.productMarketable()!!

    every { productRepository.update(sku, product) } returns mockk()

     productService.updateProduct(sku, product)

    verify(exactly = 1) { productRepository.update(sku, product) }
  }

  @Test
  fun should_delete_product() {

    val sku: Long = 43264

    ProductTestData.productMarketable()!!

    every { productRepository.delete(sku) } returns mockk()

    productService.deleteProduct(sku)

    verify(exactly = 1) { productRepository.delete(sku) }
  }

}
