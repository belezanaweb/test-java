package br.com.blz.testjava.controller

import br.com.blz.testjava.application.exception.handling.ControllerExceptionHandler
import br.com.blz.testjava.application.exception.handling.FieldError
import br.com.blz.testjava.application.exception.handling.StandardError
import br.com.blz.testjava.controller.dto.ProductDTO
import br.com.blz.testjava.domain.entities.Inventory
import br.com.blz.testjava.domain.entities.Product
import br.com.blz.testjava.domain.entities.Warehouse
import br.com.blz.testjava.domain.entities.WarehouseType
import br.com.blz.testjava.domain.repositories.ProductRepository
import br.com.blz.testjava.domain.services.ProductService
import com.beust.klaxon.Klaxon
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import javax.validation.Validator


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class ProductControllerTest {

  private val productRepository: ProductRepository = mockk()

  val mockMvc: MockMvc = standaloneSetup(ProductController(ProductService(productRepository)))
                          .setControllerAdvice(ControllerExceptionHandler())
                          .build()

  @LocalServerPort
  private var randomServerPort = 0

  val v: Validator? = null

  @BeforeEach
  fun init() {
    clearMocks(productRepository)
  }

  @Test
  fun `Verify endpoint to find all products`() {
    val warehouses = listOf<Warehouse>(Warehouse("PR", 100, WarehouseType.ECOMMERCE))
    val inventory = Inventory(warehouses)
    val product = Product(123456, "Product 1", inventory)
    val products = listOf<Product>(product)

    every{ productRepository.findAll() } returns products

    val httpResponse = mockMvc.perform(get("http://localhost:$randomServerPort/products")
      .contentType(MediaType.APPLICATION_JSON))
      .andReturn()

    assertEquals(200, httpResponse.response.status)

  }

  @Test
  fun `Verify endpoint to find a product by sku` () {
    val warehouses = listOf<Warehouse>(Warehouse("PR", 100, WarehouseType.ECOMMERCE))
    val inventory = Inventory(warehouses)
    val product = Product(222222, "Product 2", inventory)

    every{ productRepository.find(eq(222222)) } returns product

    val httpResponse = mockMvc.perform(get("http://localhost:$randomServerPort/products/222222")
      .contentType(MediaType.APPLICATION_JSON))
      .andReturn()

    assertEquals(200, httpResponse.response.status)
  }

  @Test
  fun `Verify endpoint to find a product by sku with an invalid sku` () {
    every{ productRepository.find(eq(222222)) } returns null

    val httpResponse = mockMvc.perform(get("http://localhost:$randomServerPort/products/222222")
      .contentType(MediaType.APPLICATION_JSON))
      .andReturn()

    assertEquals(404, httpResponse.response.status)
  }

  @Test
  fun `Verify endpoint to create a product`() {
    val sku: Long = 3333333
    val warehouses = listOf<Warehouse>(Warehouse("PR", 100, WarehouseType.ECOMMERCE))
    val inventory = Inventory(warehouses)
    val product = Product(sku, "Product 3", inventory)
    val productDTO = ProductDTO(product)

    every { productRepository.find(sku) } returns null
    every { productRepository.save(any()) } returns product

    val klaxon = Klaxon()
    val httpResponse = mockMvc.perform(post("http://localhost:$randomServerPort/products")
      .content(klaxon.toJsonString(productDTO))
      .contentType(MediaType.APPLICATION_JSON))
      .andReturn()

    assertEquals(201, httpResponse.response.status)
  }

  @Test
  fun `Must reject an update that a product with an empty name`() {
    val sku: Long = 3333333
    val warehouses = listOf<Warehouse>(Warehouse("PR", 100, WarehouseType.ECOMMERCE))
    val inventory = Inventory(warehouses)
    val product = Product(sku, "", inventory)
    val productDTO = ProductDTO(product)

    every { productRepository.find(sku) } returns null
    every { productRepository.save(any()) } returns product

    val klaxon = Klaxon()
    val httpResponse = mockMvc.perform(post("http://localhost:$randomServerPort/products")
        .content(klaxon.toJsonString(productDTO))
        .contentType(MediaType.APPLICATION_JSON))
        .andReturn()

    val standardError = klaxon.parse<StandardError>(httpResponse.response.contentAsString)
    assertEquals(listOf(FieldError("productDTO", "productDTO.name", "NotEmpty")), standardError?.errors)

}

  @Test
  fun `Verify endpoint to update a product`() {
    val sku: Long = 333333
    val warehouses = listOf<Warehouse>(Warehouse("PR", 100, WarehouseType.ECOMMERCE))
    val inventory = Inventory(warehouses)
    val product = Product(sku, "Product 3", inventory)
    val productDTO = ProductDTO(product).apply { name = "Product 33" }

    every { productRepository.find(sku) } returns product
    every { productRepository.update(any()) } returns productDTO.toEntity()

    val klaxon = Klaxon()
    val httpResponse = mockMvc.perform(put("http://localhost:$randomServerPort/products/$sku")
      .content(klaxon.toJsonString(productDTO))
      .contentType(MediaType.APPLICATION_JSON))
      .andReturn()

    assertEquals(200, httpResponse.response.status)
  }

  @Test
  fun `Verify endpoint to update a product with a new sku`() {
    val sku: Long = 333333
    val warehouses = listOf<Warehouse>(Warehouse("PR", 100, WarehouseType.ECOMMERCE))
    val inventory = Inventory(warehouses)
    val product = Product(sku, "Product 3", inventory)
    val productDTO = ProductDTO(product).apply { name = "Product 33" }

    every { productRepository.find(sku) } returns null
    every { productRepository.save(any()) } returns productDTO.toEntity()

    val klaxon = Klaxon()
    val httpResponse = mockMvc.perform(put("http://localhost:$randomServerPort/products/$sku")
      .content(klaxon.toJsonString(productDTO))
      .contentType(MediaType.APPLICATION_JSON))
      .andReturn()

    assertEquals(201, httpResponse.response.status)
  }

  @Test
  fun `Verify endpoints to delete a product`() {
    val sku: Long = 4444444
    val warehouses = listOf<Warehouse>(Warehouse("PR", 100, WarehouseType.ECOMMERCE))
    val inventory = Inventory(warehouses)
    val product = Product(sku, "Product 4", inventory)

    every { productRepository.find(sku) } returns product
    every { productRepository.delete(any()) } returns true

    val httpResponse = mockMvc.perform(delete("http://localhost:$randomServerPort/products/$sku")
      .contentType(MediaType.APPLICATION_JSON))
      .andReturn()

    assertEquals(200, httpResponse.response.status)

  }

  @Test
  fun `Verify endpoint to delete a product with an invalid sku`() {
    val sku: Long = 5555555

    every { productRepository.find(sku) } returns null

    val httpResponse = mockMvc.perform(delete("http://localhost:$randomServerPort/products/$sku")
      .contentType(MediaType.APPLICATION_JSON))
      .andReturn()

    assertEquals(404, httpResponse.response.status)
  }

}
