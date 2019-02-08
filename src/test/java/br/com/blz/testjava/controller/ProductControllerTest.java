package br.com.blz.testjava.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;

import br.com.blz.testjava.model.Product;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public class ProductControllerTest extends RestHelper {
	
	private static final String PRODUCTS_URI = "/products/";

	@PostConstruct
	public void init() {
		this.serverHost = "http://localhost:";
		this.serverPort = "8080";
	}

	@Test	
	public void shouldCreateProduct() {
		long sku = 2L;
		Product product = new Product(sku, "Shampoo Importado");
		
		ResponseEntity<String> postResponse = sendPOSTRequest(PRODUCTS_URI,product);
		assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
		ResponseEntity<String> getResponse = sendGETRequest(PRODUCTS_URI.concat(String.valueOf(product.getSku())));
		assertEquals(HttpStatus.OK, getResponse.getStatusCode());
		assertEquals(String.valueOf(sku) , JsonPath.parse(getResponse.getBody()).read("$.sku").toString());
	}
	
	@Test	
	public void shouldThrowErrorWhenSKUAlreadyExists() {
		long sku = 43356L;
		Product product = new Product(sku,"Shampoo Importado");
		
		ResponseEntity<String> postResponse = sendPOSTRequest(PRODUCTS_URI,product);
		assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
		
		try {
			postResponse = sendPOSTRequest(PRODUCTS_URI,product);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
		}
	}
	
	@Test
	public void shouldUpdateProduct() {
		long sku = 889L;
		String expectedName = "Shampoo Nacional";
		Product product = new Product(sku,"Shampoo Importado");
		sendPOSTRequest(PRODUCTS_URI,product);
		
		product.setName(expectedName);
		
		ResponseEntity<String> postResponse = sendPUTRequest(PRODUCTS_URI.concat(String.valueOf(product.getSku())),product);
		assertEquals(HttpStatus.OK, postResponse.getStatusCode());
		ResponseEntity<String> getResponse = sendGETRequest(PRODUCTS_URI.concat(String.valueOf(product.getSku())));
		assertEquals(HttpStatus.OK, getResponse.getStatusCode());
		assertEquals(expectedName, JsonPath.parse(getResponse.getBody()).read("$.name"));
	}
	
	@Test
	public void shouldDeleteProduct() {
		long sku = 96L;
		Product product = new Product(sku,"Shampoo Importado");
		
		ResponseEntity<String> postResponse = sendPOSTRequest(PRODUCTS_URI,product);
		assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
		sendDeleteRequest(PRODUCTS_URI.concat(String.valueOf(product.getSku())));
		try {
			sendGETRequest(PRODUCTS_URI.concat(String.valueOf(product.getSku())));
			fail("Product should not exist at this point.");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
		
	}

}
