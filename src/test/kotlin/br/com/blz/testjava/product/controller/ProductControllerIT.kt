package br.com.blz.testjava.product.controller

import br.com.blz.testjava.product.repository.ProductRepository
import br.com.blz.testjava.product.service.provider.ProductProvider
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.io.IOException

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class ProductControllerIT {

  @Autowired
  lateinit var mvc: MockMvc

  @Autowired
  lateinit var productRepository: ProductRepository

  @BeforeEach
  private fun setUp() {
    productRepository.memoryDataBase = hashMapOf()
  }

  @Test
  fun `should create product and return HTTP 201`() {
    val productInDTO = ProductProvider.createInDto()

    mvc.perform(
      post("/products").contentType(MediaType.APPLICATION_JSON).content(toJson(productInDTO))
    ).andExpect(status().isCreated)

    assertTrue(productRepository.existsBySku(productInDTO.sku))
  }

  @Test
  fun `should edit product by sku and return HTTP 200`() {
    val productInDTO = ProductProvider.createInDto()
    val sku = 123L

    mvc.perform(
      put("/products/{sku}", sku).contentType(MediaType.APPLICATION_JSON)
        .content(toJson(productInDTO))
    ).andExpect(status().isOk)

    assertTrue(productRepository.existsBySku(sku))
  }

  @Test
  fun `should find by sku the product and return HTTP 200`() {
    val productInDTO = ProductProvider.createInDto()

    mvc.perform(
      post("/products").contentType(MediaType.APPLICATION_JSON).content(toJson(productInDTO))
    )

    mvc.perform(get("/products/{sku}", productInDTO.sku)).andExpect(status().isOk)
      .andExpect(MockMvcResultMatchers.jsonPath("$.sku", equalTo(43264)))
      .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")))
      .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.quantity", equalTo(15)))
      .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[0].locality", equalTo("SP")))
      .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[0].quantity", equalTo(12)))
      .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[0].type", equalTo("ECOMMERCE")))
      .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[1].locality", equalTo("MOEMA")))
      .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[1].quantity", equalTo(3)))
      .andExpect(MockMvcResultMatchers.jsonPath("$.inventory.warehouses[1].type", equalTo("PHYSICAL_STORE")))
      .andExpect(MockMvcResultMatchers.jsonPath("$.isMarketable", equalTo(true)))
  }

  @Test
  fun `should delete product by sku and return HTTP 204`() {
    val productInDTO = ProductProvider.createInDto()

    mvc.perform(
      post("/products").contentType(MediaType.APPLICATION_JSON).content(toJson(productInDTO))
    )

    mvc.perform(delete("/products/{sku}", productInDTO.sku).content(toJson(productInDTO)))
      .andExpect(status().isNoContent)

    assertFalse(productRepository.existsBySku(productInDTO.sku))
  }

  @Throws(IOException::class)
  fun toJson(dados: Any): String {
    val mapper = ObjectMapper()
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
    return mapper.writeValueAsString(dados)
  }
}
