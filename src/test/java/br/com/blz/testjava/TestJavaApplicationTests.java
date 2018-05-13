package br.com.blz.testjava;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestJavaApplicationTests {
	
	private static final String SKU = "123456";
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testeResource() {
		
		Warehouse w = new Warehouse();
		w.setLocality("SP");
		w.setQuantity(15);
		w.setType("ECOMMERCE");
		
		Warehouse w2 = new Warehouse();
		w2.setLocality("SP");
		w2.setQuantity(15);
		w2.setType("ECOMMERCE");
		
		Inventory i = new Inventory();
		i.setWarehouses(Arrays.asList(w, w2));
		
		Product p = new Product();
		p.setInventory(i);
		p.setName("Teste");
		p.setSku(SKU);
		
		HttpEntity<Product> request = new HttpEntity<>(p);
		ResponseEntity<?> response = this.restTemplate.postForEntity("/products", request, Product.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		ResponseEntity<Product> response2 = this.restTemplate.getForEntity("/products/" + SKU, Product.class);
		assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response2.getBody().getInventory().getQuantity()).isEqualTo(30);
		assertThat(response2.getBody().isMarketable()).isTrue();
		
		response2 = this.restTemplate.getForEntity("/products/" + "789456", Product.class);
		assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		
		this.restTemplate.delete("/products/" + SKU);
		
		i.setWarehouses(new ArrayList<Warehouse>());
		p.setInventory(i);
		request = new HttpEntity<>(p);
		response = this.restTemplate.postForEntity("/products", request, Product.class);
		response2 = this.restTemplate.getForEntity("/products/" + SKU, Product.class);
		assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response2.getBody().getInventory().getQuantity()).isEqualTo(0);
		
	}
}
