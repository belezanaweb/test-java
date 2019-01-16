package br.com.blz.testjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackageClasses = TestJavaApplication.class)
@EnableCaching
public class TestJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestJavaApplication.class, args);
	}
}
