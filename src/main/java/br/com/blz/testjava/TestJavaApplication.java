package br.com.blz.testjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages= {"br.com.blz"})
@EntityScan("br.com.blz.entity")
@EnableJpaRepositories("br.com.blz.repository")
public class TestJavaApplication{

	public static void main(String[] args) {
		SpringApplication.run(TestJavaApplication.class, args);
	}
}
