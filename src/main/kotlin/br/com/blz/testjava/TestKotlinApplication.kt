package br.com.blz.testjava

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class TestKotlinApplication

fun main(args: Array<String>) {
  SpringApplication.run(TestKotlinApplication::class.java, *args);
}
