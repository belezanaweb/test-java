package br.com.blz.testjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.blz.testjava")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
