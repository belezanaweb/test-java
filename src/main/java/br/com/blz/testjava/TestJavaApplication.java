package br.com.blz.testjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackageClasses = TestJavaApplication.class)
@EntityScan(basePackages = "br.com.blz.testjava.model")
@Configuration
public class TestJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestJavaApplication.class, args);
	}
}
