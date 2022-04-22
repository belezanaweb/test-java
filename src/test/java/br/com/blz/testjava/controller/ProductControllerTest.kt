package br.com.blz.testjava.controller

import br.com.blz.testjava.enums.TypeWarehouse
import br.com.blz.testjava.exceptions.CustomNotFoundException
import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.model.Warehouse
import br.com.blz.testjava.repository.ProductRepository
import br.com.blz.testjava.service.ProductService
import br.com.blz.testjava.util.fakeProduct
import br.com.blz.testjava.util.toRequest
import br.com.blz.testjava.util.toUpdateRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

import kotlin.random.Random

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@ActiveProfiles("test")
class ProductControllerTest {

  @Autowired
  private lateinit var mapper: ObjectMapper

  @Autowired
  private lateinit var mockMvc: MockMvc

  @Autowired
  private lateinit var productService: ProductService

  @Autowired
  private lateinit var productRepository: ProductRepository

  @BeforeEach
  fun setup() = productRepository.deleteAll()

  @AfterEach
  fun tearDown() = productRepository.deleteAll()

  @Test
  fun `should return all products`() {
    val sku = Random.nextLong(from = 1, until = 100)
    val fakeProduct = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)
    productRepository.save(fakeProduct)
    val sku2 = Random.nextLong(from = 101, until = 200)
    val fakeProduct2 = fakeProduct(sku2, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)
    productRepository.save(fakeProduct2)

    mockMvc.perform(get("/products"))
      .andExpect(status().isOk)
      .andExpect(jsonPath("$[0].sku").value(fakeProduct.sku))
      .andExpect(jsonPath("$[0].name").value(fakeProduct.name))
      .andExpect(jsonPath("$[0].inventory.warehouses[0].type").value(fakeProduct.inventory.warehouses[0].type.toString()))
      .andExpect(jsonPath("$[0].inventory.warehouses[0].quantity").value(fakeProduct.inventory.warehouses[0].quantity))
      .andExpect(jsonPath("$[0].inventory.warehouses[0].locality").value(fakeProduct.inventory.warehouses[0].locality))
      .andExpect(jsonPath("$[1].sku").value(fakeProduct2.sku))
      .andExpect(jsonPath("$[1].name").value(fakeProduct2.name))
      .andExpect(jsonPath("$[1].inventory.warehouses[0].type").value(fakeProduct2.inventory.warehouses[0].type.toString()))
      .andExpect(jsonPath("$[1].inventory.warehouses[0].quantity").value(fakeProduct2.inventory.warehouses[0].quantity))
      .andExpect(jsonPath("$[1].inventory.warehouses[0].locality").value(fakeProduct2.inventory.warehouses[0].locality))
      .andReturn()
  }

  @Test
  fun `should find a product by sku`() {
    val sku = Random.nextLong(from = 1, until = 9999)
    val fakeProduct = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)
    productRepository.save(fakeProduct)

    mockMvc.perform(get("/products/$sku"))
      .andExpect(status().isOk)
      .andExpect(jsonPath("$.sku").value(fakeProduct.sku))
      .andExpect(jsonPath("$.name").value(fakeProduct.name))
      .andExpect(jsonPath("$.inventory.warehouses[0].type").value(fakeProduct.inventory.warehouses[0].type.toString()))
      .andExpect(jsonPath("$.inventory.warehouses[0].quantity").value(fakeProduct.inventory.warehouses[0].quantity))
      .andExpect(jsonPath("$.inventory.warehouses[0].locality").value(fakeProduct.inventory.warehouses[0].locality))
      .andReturn()

    val product = productService.findBySku(sku)

    assertNotNull(product)
  }

  @Test
  fun `should return not found exception by sku`() {
    val sku = Random.nextLong(from = 1, until = 100)

    mockMvc.perform(get("/products/$sku"))
      .andExpect(status().isNotFound)
      .andExpect(jsonPath("$.message").value("Sku not found"))
      .andExpect(jsonPath("$.httpCode").value(HttpStatus.NOT_FOUND.value()))

    assertThrows<CustomNotFoundException>{productService.findBySku(sku)}
  }

  @Test
  fun `should save a product`() {
    val sku = Random.nextLong(from = 1, until = 9999)
    val request = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE).toRequest()
    mockMvc.perform(post("/products")
      .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
      .andExpect(status().isCreated)
      .andExpect(jsonPath("$.sku").value(request.sku))
      .andExpect(jsonPath("$.name").value(request.name))
      .andExpect(jsonPath("$.inventory.warehouses[0].type").value(request.inventory.warehouses[0].type.toString()))
      .andExpect(jsonPath("$.inventory.warehouses[0].quantity").value(request.inventory.warehouses[0].quantity))
      .andExpect(jsonPath("$.inventory.warehouses[0].locality").value(request.inventory.warehouses[0].locality))

    val product = productService.findBySku(sku)

    assertNotNull(product)
  }

  @Test
  fun `should be a marketable product`() {
    val sku = Random.nextLong(from = 1, until = 9999)

    val warehouse1 = Warehouse("RJ", 21, TypeWarehouse.ECOMMERCE)
    val warehouse2 = Warehouse("SP", 34, TypeWarehouse.PHYSICAL_STORE)

    val inventory = Inventory(listOf(warehouse1, warehouse2))
    val request = Product(sku, "produto fake", inventory).toRequest()

    mockMvc.perform(post("/products")
      .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
      .andExpect(status().isCreated)
      .andExpect(jsonPath("$.isMarketable").value(true))
      .andExpect(jsonPath("$.inventory.quantity").value(warehouse1.quantity.plus(warehouse2.quantity)))
  }

  @Test
  fun `should NOT be a marketable product`() {
    val sku = Random.nextLong(from = 1, until = 9999)
    val request = fakeProduct(sku, "produto fake", "RJ", 0, TypeWarehouse.ECOMMERCE).toRequest()

    mockMvc.perform(post("/products")
      .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
      .andExpect(status().isCreated)
      .andExpect(jsonPath("$.isMarketable").value(false))
      .andExpect(jsonPath("$.inventory.quantity").value(0))
  }

  @Test
  fun `should NOT duplicate product with same sku`() {
    val sku = Random.nextLong(from = 1, until = 9999)
    val request = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE).toRequest()
    mockMvc.perform(post("/products")
      .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
      .andExpect(status().isCreated)
      .andExpect(jsonPath("$.sku").value(request.sku))
      .andExpect(jsonPath("$.name").value(request.name))
      .andExpect(jsonPath("$.inventory.warehouses[0].type").value(request.inventory.warehouses[0].type.toString()))
      .andExpect(jsonPath("$.inventory.warehouses[0].quantity").value(request.inventory.warehouses[0].quantity))
      .andExpect(jsonPath("$.inventory.warehouses[0].locality").value(request.inventory.warehouses[0].locality))

    mockMvc.perform(post("/products")
      .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
      .andExpect(status().isBadRequest)
      .andExpect(jsonPath("$.message").value("validation fail"))
      .andExpect(jsonPath("$.httpCode").value(HttpStatus.BAD_REQUEST.value()))
      .andExpect(jsonPath("$.errors[0].message").value("sku already exists"))
      .andExpect(jsonPath("$.errors[0].field").value("sku"))
  }

  @Test
  fun `should update a product`() {
    val sku = Random.nextLong(from = 1, until = 100)
    val fakeProduct = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)
    productRepository.save(fakeProduct)

    val newLocality = "SP"
    val newQuantity = 10
    val request = fakeProduct(sku, "produto fake alterado", newLocality, newQuantity, TypeWarehouse.ECOMMERCE)
      .toUpdateRequest()

    mockMvc.perform(put("/products/$sku")
      .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
      .andExpect(status().isOk)
      .andExpect(jsonPath("$.sku").value(sku))
      .andExpect(jsonPath("$.name").value(request.name))
      .andExpect(jsonPath("$.inventory.warehouses[0].locality").value(newLocality))
      .andExpect(jsonPath("$.inventory.warehouses[0].quantity").value(newQuantity))
  }

  @Test
  fun `should return not found exception on update`() {
    val sku = Random.nextLong(from = 1, until = 100)
    val request = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)
      .toUpdateRequest()

    mockMvc.perform(put("/products/$sku")
      .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
      .andExpect(status().isNotFound)
      .andExpect(jsonPath("$.message").value("Sku not found"))
      .andExpect(jsonPath("$.httpCode").value(HttpStatus.NOT_FOUND.value()))

    assertThrows<CustomNotFoundException>{productService.findBySku(sku)}
  }

  @Test
  fun `should delete a product`() {
    val sku = Random.nextLong(from = 1, until = 100)
    val fakeProduct = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)
    productRepository.save(fakeProduct)

    assertNotNull(fakeProduct)

    mockMvc.perform(delete("/products/$sku"))
      .andExpect(status().isNoContent)
  }

  @Test
  fun `should return not found exception on delete`() {
    val sku = Random.nextLong(from = 1, until = 100)
    val fakeProduct = fakeProduct(sku, "produto fake", "RJ", 1, TypeWarehouse.ECOMMERCE)
    productRepository.save(fakeProduct)

    assertNotNull(fakeProduct)

    mockMvc.perform(delete("/products/$sku"))
      .andExpect(status().isNoContent)

    assertThrows<CustomNotFoundException>{productService.findBySku(sku)}
  }
}
