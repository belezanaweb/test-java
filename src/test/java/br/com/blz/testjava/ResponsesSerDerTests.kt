package br.com.blz.testjava

import br.com.blz.testjava.TestFixtures.responseInstanceFixture
import br.com.blz.testjava.TestFixtures.responseJsonFixture
import br.com.blz.testjava.model.responses.ProductResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ResponsesSerDerTests {

  private val mapper = jacksonObjectMapper()

  @Test
  fun `from json to instance`() {
    val state = mapper.readValue<ProductResponse>(responseJsonFixture)
    assertEquals(state, responseInstanceFixture)
  }

  @Test
  fun `from instance to json then back`() {
    val asJson = mapper.writeValueAsString(responseInstanceFixture)
    val state = mapper.readValue<ProductResponse>(asJson)
    assertEquals(state, responseInstanceFixture)
  }

}
