package br.com.blz.testjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("br.com.blz")
@SpringBootApplication
public class TestJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestJavaApplication.class, args);
	}
}