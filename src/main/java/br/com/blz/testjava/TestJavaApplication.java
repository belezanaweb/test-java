package br.com.blz.testjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class TestJavaApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TestJavaApplication.class, args);
	}
}
