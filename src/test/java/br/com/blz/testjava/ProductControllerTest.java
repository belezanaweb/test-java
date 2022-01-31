package br.com.blz.testjava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;


@SpringBootTest(classes = TestJavaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	
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

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/product",
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
		//headers.setBasicAuth(user, secretKey);
		HttpEntity<Product> entity = new HttpEntity<>(product, headers);
		
		ResponseEntity<Product> postResponse = restTemplate.postForEntity(getRootUrl() + "/product", entity,  Product.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
	
	
	@Test
	public void testGetProductBySKU() {
		Product product = restTemplate.getForObject(getRootUrl() + "/product/43264", Product.class);
		assertNotNull(product);
	}

	@Test
	public void testUpdateProduct() {
		Product product = new Product();
		product.setSku(43264l);
		product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
		product.setInventory(new Inventory(Arrays.asList(new Warehouse("SP", 12l, "ECOMMERCE"),new Warehouse("MOEMA", 3l, "PHYSICAL_STORE"),new Warehouse("RJ", 11L, "ECOMMERCE"))));
		

				
		restTemplate.put(getRootUrl() + "/product/" + 43264l, product);		

		Product updatedProduct = restTemplate.getForObject(getRootUrl() + "/product/" + 43264l, Product.class);
		assertNotNull(updatedProduct);
		assertEquals(updatedProduct.getInventory().getQuantity(), 26l);
	}

	@Test
	public void testDeleteProduct() {
	
		Product product = restTemplate.getForObject(getRootUrl() + "/product/" + 43264l, Product.class);
		assertNotNull(product);

		restTemplate.delete(getRootUrl() + "/product/" + 43264l);

		try {
			product = restTemplate.getForObject(getRootUrl() + "/products/" + 43264l, Product.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}
