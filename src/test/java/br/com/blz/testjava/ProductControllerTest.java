package br.com.blz.testjava;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import br.com.blz.testjava.dto.InventoryDto;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.dto.WarehouseDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestJavaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetProductBySku() {
		ProductDto productDto = restTemplate.getForObject(getRootUrl() + "/marketplace/product/v1?sku=12",
				ProductDto.class);
		assertNotNull(productDto);
	}

	@Test
	public void testCreateProduct() {
		ProductDto productDto = ProductDto.of(12345L, "Test product name",
				InventoryDto.of(15, Arrays.asList(WarehouseDto.of("PINHEIROS", 15))), true);
		ResponseEntity<ProductDto> postResponse = restTemplate.postForEntity(getRootUrl() + "/marketplace/product/v1",
				productDto, ProductDto.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateProduct() {
		ProductDto productDto = restTemplate.getForObject(getRootUrl() + "/marketplace/product/v1?sku=12345",
				ProductDto.class);
		assertNotNull(productDto);
		productDto.setName("Test product name ALTERADO");
		restTemplate.put(getRootUrl() + "/marketplace/product/v1", productDto);
		ProductDto updatedProductDto = restTemplate.getForObject(getRootUrl() + "/marketplace/product/v1?sku=12345",
				ProductDto.class);
		assertNotNull(updatedProductDto);
	}

	@Test
	public void testDeleteProduct() {
		ProductDto productDto = restTemplate.getForObject(getRootUrl() + "/marketplace/product/v1?sku=12345",
				ProductDto.class);
		assertNotNull(productDto);
		restTemplate.delete(getRootUrl() + "/marketplace/product/v1?sku=12345");
		try {
			restTemplate.getForObject(getRootUrl() + "/marketplace/product/v1?sku=12345", ProductDto.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.OK);
		}
	}

}
