package br.com.blz.testjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TestJavaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TestJavaApplication.class, args);
	}
}
