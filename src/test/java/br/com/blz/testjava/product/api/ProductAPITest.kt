package br.com.blz.testjava.product.api

import br.com.blz.testjava.product.api.dto.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductAPITest {

  @LocalServerPort
  val localServerPort: Int = 0

  @Test
  fun `Create a product should returns 201`() {
    val restTemplate = TestRestTemplate()
    val product = ProductAPISaveInputDTO(1L, "Product 01", ProductInventoryAPISaveInputDTO(listOf()))
    val url = "http://localhost:$localServerPort/products"
    val request = HttpEntity(product)
    val response = restTemplate.postForEntity<String>(url, request)

    assertEquals(HttpStatus.CREATED, response.statusCode)
  }

  @Test
  fun `Create same product twice shout returns an error`() {
    val restTemplate = TestRestTemplate()
    val product = ProductAPISaveInputDTO(1L, "Product 01", ProductInventoryAPISaveInputDTO(listOf()))
    val url = "http://localhost:$localServerPort/products"
    val request = HttpEntity(product)

    var response = restTemplate.postForEntity<ProductAPISaveOutputDTOSuccess>(url, request)
    var response2 = restTemplate.postForEntity<ProductAPISaveOutputDTOError>(url, request)

    assertEquals(HttpStatus.CREATED, response.statusCode)
    assertEquals(HttpStatus.BAD_REQUEST, response2.statusCode)
  }
}
