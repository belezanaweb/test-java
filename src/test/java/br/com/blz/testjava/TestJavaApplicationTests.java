package br.com.blz.testjava;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.object.Inventory;
import br.com.blz.testjava.object.Product;
import br.com.blz.testjava.object.Warehouses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJavaApplicationTests {

	Product product;

	@Test
	public void contextLoads() {
	}

	@Before
	public void beforeEach() {
		product = new Product();
		product.setInventory(new Inventory());
	}

	@Test
	public void noMarketableProductTest() {
		product.setInventory(null);
		assertThat("not marketable", product.isMarketable(),
				CoreMatchers.is(false));
	}


	@Test
	public void productInventoryQuantityWithoutWarehousesTest() {
		assertThat("quantity should be (0)",
				product.getInventory().getQuantity(), CoreMatchers.is(0));
	}
	
	@Test
	public void notMarketableNoWarehousesTest() {
		assertThat("no warehouses", product.isMarketable(),
				CoreMatchers.is(false));
	}

	@Test
	public void marketableProductTest() {
		Warehouses warehouse = new Warehouses();
		warehouse.setQuantity(10);
		
		List<Warehouses> warehouseList = Arrays.asList(warehouse);
		product.getInventory().setWarehouses(warehouseList);

		assertThat("marketable", product.isMarketable(),
				CoreMatchers.is(true));
	}

	@Test
	public void notMarketableProductWithEmptyWarehousesTest() {

		Warehouses warehouse1 = new Warehouses();
		warehouse1.setQuantity(0);
		Warehouses warehouse2 = new Warehouses();
		warehouse2.setQuantity(0);

		product.getInventory().setWarehouses(Arrays.asList(warehouse1, warehouse2));

		assertThat("warehouses with zero quantity", product.isMarketable(),
				CoreMatchers.is(false));
	}

	@Test
	public void productInventoryQuantityTest() {

		Warehouses warehouse1 = new Warehouses();
		warehouse1.setQuantity(15);
		Warehouses warehouse2 = new Warehouses();
		warehouse2.setQuantity(20);
		List<Warehouses> warehouseList = new ArrayList<>();
		warehouseList.add(warehouse1);
		warehouseList.add(warehouse2);
		product.getInventory().setWarehouses(warehouseList);

		assertThat("correct quantity", product.getInventory().getQuantity(),
				CoreMatchers.is(35));
	}

}
