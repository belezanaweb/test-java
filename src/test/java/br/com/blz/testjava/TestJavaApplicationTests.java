package br.com.blz.testjava;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public abstract class TestJavaApplicationTests {

	@Value("${local.server.port}")
	protected int porta;

	@Before
	public void setUp() throws Exception {
		RestAssured.port = porta;
		System.out.println("TESTE");

	}
	
	public static void main(String[] args) {
		SpringApplication.run(TestJavaApplicationTests.class, args);
	}
}