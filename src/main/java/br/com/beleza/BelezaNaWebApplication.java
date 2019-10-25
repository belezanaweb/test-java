package br.com.beleza;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BelezaNaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelezaNaWebApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init() {
		return args -> {
			
		};
	}
}