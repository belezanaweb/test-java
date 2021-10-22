package br.com.blz.testjava;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackageClasses = TestJavaApplication.class)
public class TestJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestJavaApplication.class, args);
	}

    /**
     * Coloquei essa configuração para o Jackson que é usado no spring fazer a conversao
     * de classes do Kotlin
     */
    @Bean
    public ObjectMapper objectMapper () {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new KotlinModule());
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
