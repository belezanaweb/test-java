package br.com.blz.testjava;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.blz.testjava.repository.ProductRepository;

@SpringBootTest
class TestJavaApplicationTests {

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	void contextLoads() {
		assertThat(productRepository).isNotNull();
	}
}
