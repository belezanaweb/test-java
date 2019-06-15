package br.com.blz.testjava;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.WareHouse;
import br.com.blz.testjava.model.WareHouseTypeEnum;

@RunWith(SpringRunner.class)
@SpringBootTest( classes = TestJavaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class TestJavaApplicationTests {
	
	/**
	 * Local server port.
	 */
	@LocalServerPort
	private int port;
	
	/**
	 * URL base.
	 */
	private String url;
	
	private RestTemplate template;
			
	@Before
	public void setup() {
		this.url = "http://localhost:" + this.port + "/products";
		this.template = new RestTemplate();
	}
	
	/**
	 * Method responsible for testing product search with empty return.
	 */
	@Test
	public void findProducByApiNotFoundTest() {
		
		Product response = this.template.getForObject(url + "/1", Product.class);
		
		assertTrue("Product not found when we cache is empty.", response == null);
	}
	
	/**
	 * Method responsible for testing product search with empty return with invalid sku.
	 */
	@Test
	public void findProductApiIsEmptyBySkuInvalidSuccessTest() {
		
		Product product = this.createProduct(1234567811L);
		
		this.template.postForEntity(url, product, Product.class).getBody();
		
		Product response = this.template.getForObject(url + "/1200000000000", Product.class);
		
		assertTrue("Product not found when we cache not is empty and sku is invalid.", response == null);
	}
	
	/**
	 * Method responsible for testing product search with return ok with valid sku.
	 */
	@Test
	public void findProductApiFoundsTest() {
		
		Product product = this.createProduct(1234567812L);
		
		this.template.postForEntity(url, product, Product.class).getBody();
		
		Product response = this.template.getForObject(url + "/1234567812", Product.class);
		
		assertTrue("Product found when we inform a sku valid.", response != null);
	}
	
	/**
	 * Method responsible for successfully testing product creation.
	 */
	@Test
	public void createProducApitWithSuccessTest() {
		
		Product product = this.createProduct(1234567813L);
		
		Product response = this.template.postForEntity(url, product, Product.class).getBody();
		
		assertTrue("Product is created with succes when we inform a data valid.", response != null);
	}
	
	/**
	 * Method responsible for testing product and product failure because the product already exists.
	 */
	@Test()
	public void createProductApiWithErrorTest() {
		
		Product product = this.createProduct(1234567814L);
		
		this.template.postForEntity(url, product, Product.class).getBody();

		try {
			this.template.postForEntity(url, product, Product.class).getBody();
		}catch (HttpClientErrorException e) {
			assertTrue("Product not was created with succes.", e.getStatusCode().name().equals("NOT_ACCEPTABLE"));
		}
	}
	
	/**
	 * Method responsible for successfully testing the product update.
	 */
	@Test
	public void updateProductApiWithSuccessTest() {
		
		Product product = this.createProduct(1234567815L);
		
		this.template.postForEntity(url, product, Product.class).getBody();

		product.setName("Balck Bean");
		boolean response = this.template.exchange(url, HttpMethod.PUT, new HttpEntity<Product>(product), Boolean.class).getBody();
		
		assertTrue("Product is updated with succes when we inform a data valid.", response);
	}
	
	/**
	 * Method responsible for testing product update failure because the product was not found.
	 */
	@Test
	public void updateProductApiWithErrorTest() {
		
		Product product = this.createProduct(1234567816L);
		
		try {
			this.template.exchange(url, HttpMethod.PUT, new HttpEntity<Product>(product), Product.class).getBody();
		} catch (HttpClientErrorException e) {
			assertTrue("Product not was created with succes.", e.getStatusCode().name().equals("NOT_FOUND"));
		}
	}
	
	/**
	 * Method responsible for successfully deleting product.
	 */
	@Test
	public void deleteProducApitWithSuccessTest() {
		
		Product product = this.createProduct(1234567817L);
		
		this.template.postForEntity(url, product, Product.class).getBody();

		boolean isDeleted = this.template.exchange(url + "/1234567817", HttpMethod.DELETE, new HttpEntity<String>(""), Boolean.class).getBody();
		
		assertTrue("Product is deleted with succes when we inform a sku valid.", isDeleted);
	}
	
	/**
	 * Method responsible for deleting product with error because product was not found.
	 */
	@Test
	public void deleteProductApiWithErrorTest() {
		
		Product product = this.createProduct(1234567818L);
		
		this.template.postForEntity(url, product, Product.class).getBody();

		try {
			this.template.exchange(url + "/1234567819", HttpMethod.DELETE, new HttpEntity<String>(""), Boolean.class).getBody();
		} catch (HttpClientErrorException e) {
			assertTrue("Product not was deleted with succes.", e.getStatusCode().name().equals("NOT_FOUND"));
		}
			
	}
	
	/**
	 * Method responsible to create the Product.
	 * @param sku Product SKU
	 * @return Product
	 */
	private Product createProduct(Long sku) {
		
		Inventory inv = new Inventory();
		inv.setWareHouses(new ArrayList<>());
		
		WareHouse wh = new WareHouse();
		wh.setLocality("RJ");
		wh.setQuantity(2L);
		wh.setType(WareHouseTypeEnum.ECOMMERCE);
		
		inv.getWareHouses().add(wh);
		
		Product product = new Product();
		product.setName("Bean");
		product.setSku(sku);
		product.setInventory(inv);
		
		return product;
	}

}
