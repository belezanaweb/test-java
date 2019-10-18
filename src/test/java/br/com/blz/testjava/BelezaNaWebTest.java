package br.com.blz.testjava;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.model.WarehouseType;
import io.restassured.http.ContentType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestJavaApplicationTests.class })
public class BelezaNaWebTest extends TestJavaApplicationTests {
	
	
	Product product = new Product();
	
	@Before
	public void populateProduct() {
		product.setSku(123L);
		product.setName("TEST PRODUCT");
		Inventory inventory = new Inventory();
		inventory.setCodigo(1L);
		Warehouse warehouse1 = new Warehouse();
		//warehouse1.setCodigo(0L);
		warehouse1.setLocality("SP");
		warehouse1.setQuantity(12L);
		warehouse1.setType(WarehouseType.ECOMMERCE);
		List<Warehouse> listWarehouses = new ArrayList<Warehouse>();
		listWarehouses.add(warehouse1);
		
		
		Warehouse warehouse2 = new Warehouse();
		//warehouse2.setCodigo(1L);
		warehouse2.setLocality("SP");
		warehouse2.setQuantity(12L);
		warehouse2.setType(WarehouseType.PHYSICAL_STORE);
		
		listWarehouses.add(warehouse2);
		inventory.setWarehouses(listWarehouses);
		product.setInventory(inventory);
		
	}
	
	@Test
	public void deveInserirProduto() throws Exception {		
				
		given().contentType(ContentType.JSON).
			body(product).
		when().
			post("/product").
		then().
			log().body().
			and().statusCode(201);
	}
}
