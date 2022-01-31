package br.com.blz.testjava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.google.gson.Gson;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;


@SpringBootTest(classes = TestJavaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Value("${app.basic.user}")
	private String user;

	@Value("${app.basic.secretKey}")
	private String secretKey;
	
	
	@LocalServerPort
	private int port;
	

	private String getRootUrl() {
		return "http://localhost:" + port+"/api/v1";
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testList() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.withBasicAuth(user, secretKey).exchange(getRootUrl() + "/product",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	

	@Test
	public void testCreateProduct() {
		Product product = new Product();
		product.setSku(43264l);
		product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
		product.setInventory(new Inventory(Arrays.asList(new Warehouse("SP", 12l, "ECOMMERCE"),new Warehouse("MOEMA", 3l, "PHYSICAL_STORE"))));
		

		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(user, secretKey);
		HttpEntity<Product> entity = new HttpEntity<>(product, headers);
		
		ResponseEntity<Product> postResponse = restTemplate.withBasicAuth(user, secretKey).postForEntity(getRootUrl() + "/product", entity,  Product.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
	
	
	@Test
	public void testGetProductBySKU() {
		
		testCreateProduct();		
		
		Product product = restTemplate.withBasicAuth(user, secretKey).getForObject(getRootUrl() + "/product/" + 43264l, Product.class);
		
		assertNotNull(product);
		assertEquals(product.getName(), "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
	}

	@Test
	public void testUpdateProduct() {
		
		testCreateProduct();
		
		Product product = restTemplate.withBasicAuth(user, secretKey).getForObject(getRootUrl() + "/product/" + 43264l, Product.class);
		
			
		assertEquals(product.getInventory().getQuantity(), 15l);
		
		
		product.setInventory(new Inventory(Arrays.asList(new Warehouse("SP", 12l, "ECOMMERCE"),new Warehouse("MOEMA", 3l, "PHYSICAL_STORE"),new Warehouse("RJ", 11L, "ECOMMERCE"))));
		
			
		restTemplate.withBasicAuth(user, secretKey).put (getRootUrl() + "/product/" + 43264l, product);		

		Product updatedProduct = restTemplate.withBasicAuth(user, secretKey).getForObject(getRootUrl() + "/product/" + 43264l, Product.class);
		assertNotNull(updatedProduct);
		assertEquals(updatedProduct.getInventory().getQuantity(), 26l);
	}

	@Test
	public void testDeleteProduct() {
		
		testCreateProduct();
	
		Product product = restTemplate.withBasicAuth(user, secretKey).getForObject(getRootUrl() + "/product/" + 43264l, Product.class);
		assertNotNull(product);

		restTemplate.withBasicAuth(user, secretKey).delete(getRootUrl() + "/product/" + 43264l);

		try {
			product = restTemplate.withBasicAuth(user, secretKey).getForObject(getRootUrl() + "/products/" + 43264l, Product.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
	


}
