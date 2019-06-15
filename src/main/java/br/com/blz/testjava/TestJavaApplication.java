package br.com.blz.testjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class responsible for initializing the application.
 * @author Andre Barroso
 *
 */
@SpringBootApplication(scanBasePackageClasses = TestJavaApplication.class)
public class TestJavaApplication {

	/**
	 * Main bloc
	 * @param args Arguments not used.
	 */
	public static void main(String[] args) {
		SpringApplication.run(TestJavaApplication.class, args);
	}
}
