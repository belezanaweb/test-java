package br.com.blz.testjava

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestJavaApplication

fun main(args: Array<String>) {
	runApplication<TestJavaApplication>(*args)
}
