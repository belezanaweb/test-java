package br.com.blz.testjava.service

import br.com.blz.testjava.enums.TypeWarehouse
import br.com.blz.testjava.exceptions.CustomNotFoundException
import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.model.Warehouse
import br.com.blz.testjava.repository.ProductRepository
import br.com.blz.testjava.util.fakeProduct
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.random.Random

@ExtendWith(MockKExtension::class)
class ProductServiceTest {

  @MockK
  private lateinit var productRepository: ProductRepository

  @InjectMockKs
  @SpyK
  private lateinit var productService: ProductService

  @Test
  fun `should save a product`() {
    val sku = Random.nextLong(from = 1, until = 9999)
    val fakeProduct = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)

    every { productRepository.save(fakeProduct) } returns fakeProduct
    val saved = productService.save(fakeProduct)

    assertEquals(fakeProduct, saved)

    verify(exactly = 1) {  productRepository.save(fakeProduct) }
    verify(exactly = 1) {  productService.save(fakeProduct) }
  }

  @Test
  fun `should find a product by sku`() {
    val sku = Random.nextLong(from = 1, until = 9999)

    val fakeProduct = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)

    every { productService.findBySku(sku)} returns fakeProduct

    val product = productService.findBySku(sku)

    assertEquals(fakeProduct, product)

    verify (exactly = 1) { productService.findBySku(sku)  }
  }

  @Test
  fun `should throw CustomNotFoundException when not found product by sku`() {

    val sku = Random.nextLong(from = 1, until = 9999)
    every { productService.findBySku(sku)} throws CustomNotFoundException("Sku not found")

    val error = assertThrows<CustomNotFoundException> {
      productService.findBySku(sku)
    }

    assertEquals("Sku not found", error.message)

    verify (exactly = 1) { productService.findBySku(sku)  }
  }

  @Test
  fun `should return all products`() {
    val sku = Random.nextLong(from = 1, until = 9999)
    val sku2 = Random.nextLong(from = 1, until = 9999)

    val fakeProduct = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)
    val fakeProduct2 = fakeProduct(sku2, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)

    val fakeProducts = listOf<Product?>(fakeProduct, fakeProduct2)

    every { productRepository.findAll() } returns fakeProducts

    val products: List<Product?> = productService.findAll()

    assertEquals(products, fakeProducts)

    verify (exactly = 1) { productRepository.findAll() }
    verify (exactly = 1) { productService.findAll() }
  }

  @Test
  fun `should delete a product by sku`() {
    val sku = Random.nextLong(from = 1, until = 9999)
    val fakeProduct = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)

    every { productRepository.findBySku(sku)} returns fakeProduct
    every { productRepository.delete(fakeProduct) } just runs

    productService.delete(sku)

    verify (exactly = 1){ productRepository.findBySku(sku) }
    verify (exactly = 1){ productRepository.delete(fakeProduct) }
  }

  @Test
  fun `should throw CustomNotFoundException when delete a product by sku`() {
    val sku = Random.nextLong(from = 1, until = 9999)

    every { productRepository.findBySku(sku)} throws CustomNotFoundException("Sku $sku not found")

    val error = assertThrows<CustomNotFoundException> { productService.delete(sku) }

    assertEquals("Sku $sku not found", error.message)

    verify (exactly = 1){ productRepository.findBySku(sku) }
    verify (exactly = 0){ productRepository.delete(any()) }
  }

}
