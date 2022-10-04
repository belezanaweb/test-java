package br.com.blz.testjava.controller

import br.com.blz.testjava.TestJavaApplication
import br.com.blz.testjava.model.Product
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(
  classes = arrayOf(TestJavaApplication::class),
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
internal class ProductControllerTest {

  @Autowired
  lateinit var restTemplate: TestRestTemplate


  @Test
  fun should_create_product() {
   // val result = restTemplate.postForObject("/products",, Product::class.java);
    //println(result)
  }

}
