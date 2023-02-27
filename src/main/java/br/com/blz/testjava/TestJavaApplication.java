package br.com.blz.testjava;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.InMemoryProductRepository;
import br.com.blz.testjava.repository.Repository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackageClasses = TestJavaApplication.class)
public class TestJavaApplication {

    private List<Product> products = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(TestJavaApplication.class, args);
	}

    @Bean
    public Repository getRepository() {
        return new InMemoryProductRepository(products);
    }

}
