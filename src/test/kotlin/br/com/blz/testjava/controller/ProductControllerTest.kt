package br.com.blz.testjava.controller

import br.com.blz.testjava.application.exception.NotFoundException
import br.com.blz.testjava.application.exception.handling.ControllerExceptionHandler
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
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import org.springframework.web.method.HandlerMethod
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod
import java.lang.reflect.Method


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class ProductControllerTest {

  private val productRepository: ProductRepository = mockk()

  val mockMvc: MockMvc = standaloneSetup(ProductController(ProductService(productRepository)))
                          .setHandlerExceptionResolvers(createExceptionResolver())
                          .build()

  @LocalServerPort
  private var randomServerPort = 0

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
    val warehouses = listOf<Warehouse>(Warehouse("PR", 100, WarehouseType.ECOMMERCE))
    val inventory = Inventory(warehouses)
    val product = Product(null, "Product 3", inventory)
    val productDTO = ProductDTO(product)

    every { productRepository.save(any()) } returns product.apply { sku = 333333 }

    val klaxon = Klaxon()
    val httpResponse = mockMvc.perform(post("http://localhost:$randomServerPort/products")
      .content(klaxon.toJsonString(productDTO))
      .contentType(MediaType.APPLICATION_JSON))
      .andReturn()

    assertEquals(201, httpResponse.response.status)
  }

  @Test
  fun `Verify endpoint to update a product`() {
    val sku: Long = 333333
    val warehouses = listOf<Warehouse>(Warehouse("PR", 100, WarehouseType.ECOMMERCE))
    val inventory = Inventory(warehouses)
    val product = Product(sku, "Product 3", inventory)
    val productDTO = ProductDTO(product).apply { name = "Product 33" }

    every { productRepository.update(any()) } returns productDTO.toEntity()

    val klaxon = Klaxon()
    val httpResponse = mockMvc.perform(put("http://localhost:$randomServerPort/products")
      .content(klaxon.toJsonString(productDTO))
      .contentType(MediaType.APPLICATION_JSON))
      .andReturn()

    assertEquals(200, httpResponse.response.status)
  }

  @Test
  fun `Verify endpoint to update a product with an invalid sku`() {
    val sku: Long = 333333
    val warehouses = listOf<Warehouse>(Warehouse("PR", 100, WarehouseType.ECOMMERCE))
    val inventory = Inventory(warehouses)
    val product = Product(sku, "Product 3", inventory)
    val productDTO = ProductDTO(product).apply { name = "Product 33" }

    every { productRepository.update(any()) } throws NotFoundException("Product not found with sku $sku")

    val klaxon = Klaxon()
    val httpResponse = mockMvc.perform(put("http://localhost:$randomServerPort/products")
      .content(klaxon.toJsonString(productDTO))
      .contentType(MediaType.APPLICATION_JSON))
      .andReturn()

    val response = httpResponse.response
    val error = Klaxon().parse<StandardError>(response.contentAsString)

    assertEquals("Product not found with sku $sku", error?.message)
    assertEquals(500, response.status)
  }

  @Test
  fun `Verify endpoints to create and delete a product`() {
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

  private fun createExceptionResolver(): ExceptionHandlerExceptionResolver {
    val exceptionResolver: ExceptionHandlerExceptionResolver = object : ExceptionHandlerExceptionResolver() {
      override fun getExceptionHandlerMethod(
        handlerMethod: HandlerMethod?, exception: Exception
      ): ServletInvocableHandlerMethod {
        val method = ExceptionHandlerMethodResolver(
          ControllerExceptionHandler::class.java
        ).resolveMethod(exception)
        return ServletInvocableHandlerMethod(
          ControllerExceptionHandler(), method as Method
        )
      }
    }
    exceptionResolver.messageConverters.add(MappingJackson2HttpMessageConverter())
    exceptionResolver.afterPropertiesSet()
    return exceptionResolver
  }

}
