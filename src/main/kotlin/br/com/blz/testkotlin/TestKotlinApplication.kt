package br.com.blz.testkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestKotlinApplication

fun main(args: Array<String>) {
  runApplication <TestKotlinApplication>(*args)
}
