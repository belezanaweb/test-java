package br.com.blz.testjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.blz.config.RestExceptionHandler;

@EntityScan(basePackages = "br.com.blz..model")
@EnableJpaRepositories(basePackages = "br.com.blz.repository")
@Import(RestExceptionHandler.class)
@SpringBootApplication(scanBasePackages = { "br.com.blz.controller","br.com.blz.service", "br.com.blz.repository", "br.com.blz.service" })
public class TestJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestJavaApplication.class, args);
	}
}
