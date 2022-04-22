package br.com.blz.testjava.repository

import br.com.blz.testjava.enums.TypeWarehouse
import br.com.blz.testjava.exceptions.CustomNotFoundException
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.util.fakeProduct
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


@ExtendWith(MockKExtension::class)
@SpringBootTest(properties = ["spring.profiles.active:test"])
class ProductRepositoryTest {

  @Autowired
  private lateinit var productRepository: ProductRepository

  @BeforeEach
  fun setup() = productRepository.deleteAll()

  @AfterEach
  fun tearDown() = productRepository.deleteAll()

  @Test
  fun `should save a product`() {
    val sku = Random.nextLong(from = 1, until = 9999)
    val fakeProduct = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)
    val product = productRepository.save(fakeProduct)

    assertNotNull(product)
    assertEquals(sku, product.sku)
  }

  @Test
  fun `should find a product by sku`() {
    val sku = Random.nextLong(from = 1, until = 9999)
    val fakeProduct = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)

    productRepository.save(fakeProduct)

    val product = assertDoesNotThrow { productRepository.findBySku(sku) }

    assertEquals(sku, product.sku)
  }

  @Test
  fun `should return exception when sku not exists`() {
    val sku = Random.nextLong(from = 1, until = 9999)
    assertThrows<CustomNotFoundException> { productRepository.findBySku(sku) }
  }

  @Test
  fun `should return all products`() {
    val sku = Random.nextLong(from = 1, until = 100)
    val fakeProduct1 = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)
    productRepository.save(fakeProduct1)

    val sku2 = Random.nextLong(from = 101, until = 200)
    val fakeProduct2 = fakeProduct(sku2, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)
    productRepository.save(fakeProduct2)

    val products = productRepository.findAll()

    assertNotNull(products)
    assertEquals(listOf(fakeProduct1, fakeProduct2), products)
  }

  @Test
  fun `should delete a product`() {
    val sku = Random.nextLong(from = 1, until = 100)
    val fakeProduct = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)
    val product = productRepository.save(fakeProduct)

    assertNotNull(product)

    productRepository.delete(product)

    assertThrows<CustomNotFoundException> { productRepository.findBySku(sku) }
  }

  @Test
  fun `should delete all products`() {
    val sku = Random.nextLong(from = 1, until = 100)
    val fakeProduct = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)
    productRepository.save(fakeProduct)
    val sku2 = Random.nextLong(from = 1, until = 100)
    val fakeProduct2 = fakeProduct(sku2, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)
    productRepository.save(fakeProduct2)

    val products = productRepository.findAll()

    assertNotNull(products)

    productRepository.deleteAll()

    assertEquals(listOf<Product>(),  productRepository.findAll())
  }
}
