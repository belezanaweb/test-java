package br.com.blz.testjava.controller

import br.com.blz.testjava.ProductTestData
import br.com.blz.testjava.cache.ProductCacheContext
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertNull


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
internal class ProductControllerTest(
  @Autowired val mockMvc: MockMvc,
) {

  @BeforeEach
  internal fun setUp() {
    ProductCacheContext.clear()
  }

  @Test
  fun should_not_get_sucessfuly_response_body_product_not_found_http_404() {
    val sku = 1234
    val result = mockMvc.perform(get("/products/$sku")).andExpect(status().isNotFound).andReturn()

    assertEquals("Product with sku [1234] was not found", result.response.contentAsString)
  }

  @Test
  fun should_get_product_sucessfuly() {
    val sku: Long = 43264

    val productGetMocked = ProductTestData.produtoCompletoTesteGithubMockado()

    ProductCacheContext.save(productGetMocked!!)


    val result = mockMvc.perform(get("/products/$sku")).andExpect(status().isOk).andDo(print()).andReturn()

    assertEquals(
      "{\"sku\":43264,\"name\":\"L'OrÃ©al Professionnel Expert Absolut Repair Cortex Lipidium - MÃ¡scara de ReconstruÃ§Ã£o 500g\",\"inventory\":{\"warehouses\":[{\"locality\":\"SP\",\"quantity\":12,\"type\":\"ECOMMERCE\"},{\"locality\":\"MOEMA\",\"quantity\":3,\"type\":\"PHYSICAL_STORE\"}],\"quantity\":15},\"isMarketable\":true}",
      result.response.contentAsString
    )
  }

  @Test
  fun should_post_sucessfuly_new_product() {
    val sku: Long = 43264

    val productMockedJson = ProductTestData.produtoCompletoTesteGithubString()

    val result = mockMvc.perform(
      post("/products").contentType(MediaType.APPLICATION_JSON)
        .content(productMockedJson)
    ).andExpect(status().isCreated).andDo(print()).andReturn()


    val savedProduct = ProductCacheContext.get(43264)

    assertEquals(
      "{\"sku\":43264,\"name\":\"L'OrÃ©al Professionnel Expert Absolut Repair Cortex Lipidium - MÃ¡scara de ReconstruÃ§Ã£o 500g\",\"inventory\":{\"warehouses\":[{\"locality\":\"SP\",\"quantity\":12,\"type\":\"ECOMMERCE\"},{\"locality\":\"MOEMA\",\"quantity\":3,\"type\":\"PHYSICAL_STORE\"}]}}",
      result.response.contentAsString
    )
    assertEquals(sku, savedProduct!!.sku)
  }

  @Test
  fun should_not_post_sucessfuly_product_already_exists_http_409_conflict() {

    val productMocked = ProductTestData.produtoCompletoTesteGithubMockado()

    ProductCacheContext.save(productMocked!!)

    val result = mockMvc.perform(
      post("/products").contentType(MediaType.APPLICATION_JSON)
        .content(Gson().toJson(productMocked))
    ).andExpect(status().isConflict).andDo(print()).andReturn()

    val responseMessage = "Product with sku [43264] already exists"

    assertEquals(responseMessage, result.response.contentAsString)
  }

  @Test
  fun should_update_sucessfuly() {
    val sku: Long = 43264

    val productMocked = ProductTestData.produtoCompletoTesteGithubMockado()

    ProductCacheContext.save(productMocked!!)

    val updatedProduct = productMocked.copy(name = "produto")

    val result = mockMvc.perform(
      put("/products/$sku").contentType(MediaType.APPLICATION_JSON)
        .content(Gson().toJson(updatedProduct))
    ).andExpect(status().isCreated).andDo(print()).andReturn()

    val responseMessage =
      "{\"sku\":43264,\"name\":\"produto\",\"inventory\":{\"warehouses\":[{\"locality\":\"SP\",\"quantity\":12,\"type\":\"ECOMMERCE\"},{\"locality\":\"MOEMA\",\"quantity\":3,\"type\":\"PHYSICAL_STORE\"}]}}"

    assertEquals(responseMessage, result.response.contentAsString)

    assertEquals(ProductCacheContext.get(sku)!!.name, "produto")
  }


  @Test
  fun should_delete_sucessfuly() {
    val sku: Long = 43264

    val productMocked = ProductTestData.produtoCompletoTesteGithubMockado()

    ProductCacheContext.save(productMocked!!)

    mockMvc.perform(
      delete("/products/$sku")
    ).andExpect(status().isNoContent).andDo(print()).andReturn()

    assertNull(ProductCacheContext.get(sku))
  }

}
