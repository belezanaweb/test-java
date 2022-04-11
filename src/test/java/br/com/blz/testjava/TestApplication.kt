package br.com.blz.testjava

import br.com.blz.testjava.TestFixtures.requestInstanceFixture
import br.com.blz.testjava.TestFixtures.requestJsonFixture
import br.com.blz.testjava.model.responses.ProductResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ProductController::class)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestJavaApplicationTests(@Autowired val mockMvc: MockMvc) {

  private val mapper = jacksonObjectMapper()
  private val changed = requestInstanceFixture.copy(name = "other name")

  @Test
  @Order(1)
  fun `home`() {
    mockMvc
      .perform(get("/")
      )
      .andExpect(status().isOk)
  }

  @Test
  @Order(2)
  fun `not found`() {
    mockMvc
      .perform(get("/products/{sku}", "43264")
        .accept(APPLICATION_JSON)
      )
      .andExpect(status().isNotFound)
  }

  @Test
  @Order(3)
  fun `created`() {
    mockMvc
      .perform(post("/products")
        .accept(APPLICATION_JSON)
        .contentType(APPLICATION_JSON)
        .content(requestJsonFixture)
      )
      .andExpect(status().isCreated)
  }

  @Test
  @Order(4)
  fun `created conflict`() {
    mockMvc
      .perform(post("/products")
        .accept(APPLICATION_JSON)
        .contentType(APPLICATION_JSON)
        .content(requestJsonFixture)
      )
      .andExpect(status().isConflict)
  }

  @Test
  @Order(5)
  fun `found`() {
    val mvcResult = mockMvc
      .perform(get("/products/{sku}", "43264")
        .accept(APPLICATION_JSON)
      )
      .andExpect(status().isOk).andReturn()
    val state = mapper.readValue<ProductResponse>(mvcResult.response.contentAsString)
    assertEquals(state, TestFixtures.responseInstanceFixture)
  }

  @Test
  @Order(6)
  fun `updated`() {
    val asJson = mapper.writeValueAsString(changed)
    mockMvc
      .perform(put("/products/{sku}", "43264")
        .contentType(APPLICATION_JSON)
        .content(asJson)
      )
      .andExpect(status().isOk)
  }

  @Test
  @Order(7)
  fun `updated result check`() {
    val mvcResult = mockMvc
      .perform(get("/products/{sku}", "43264")
        .accept(APPLICATION_JSON)
      )
      .andExpect(status().isOk).andReturn()
    val state = mapper.readValue<ProductResponse>(mvcResult.response.contentAsString)
    assertEquals(state.name, "other name")
  }

  @Test
  @Order(8)
  fun `updated not found`() {
    mockMvc
      .perform(put("/products/{sku}", "33")
        .contentType(APPLICATION_JSON)
        .content(requestJsonFixture)
      )
      .andExpect(status().isNotFound)
  }

  @Test
  @Order(9)
  fun `deleted ok`() {
    mockMvc
      .perform(delete("/products/{sku}", "43264")
      )
      .andExpect(status().isOk)
  }

  @Test
  @Order(10)
  fun `deleted not found`() {
    mockMvc
      .perform(delete("/products/{sku}", "0")
      )
      .andExpect(status().isNotFound)
  }

}
