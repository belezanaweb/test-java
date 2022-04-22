package br.com.blz.testjava

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest;


@ExtendWith(MockKExtension::class)
@SpringBootTest
class TestJavaApplicationTests {
  @Test
  fun contextLoads() {
    println("rodando test")
  }
}
