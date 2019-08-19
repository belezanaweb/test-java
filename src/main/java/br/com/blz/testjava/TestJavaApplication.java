package br.com.blz.testjava;

import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.util.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication(scanBasePackageClasses = TestJavaApplication.class)
public class TestJavaApplication {

	public static void main(String[] args) {

        Data.createData();

		SpringApplication.run(TestJavaApplication.class, args);
	}
}
