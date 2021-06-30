package br.com.blz.testjava

import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [TestJavaApplication::class])
@TestPropertySource(locations = ["classpath:application.yml"])
class TestJavaApplicationTests {

  @Test
  fun contextLoads() {
  }

}
