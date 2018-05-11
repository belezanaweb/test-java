package br.com.blz.testjava;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class TestJavaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new TestJavaApplication().configure(new SpringApplicationBuilder(TestJavaApplication.class)).run(args);
		
	}
}
