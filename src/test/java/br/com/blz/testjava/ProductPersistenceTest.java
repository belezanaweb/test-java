package br.com.blz.testjava;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.exception.BusinessException;
import br.com.blz.testjava.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductPersistenceTest {

	@Autowired
	private ProductService productService;

	Product product;

	@Before
	public void setUp() {

		product = criarProduto();
	}

	@Test
	public void saveSuccess() {

		productService.save(product);
		Assert.assertTrue(true);
	}

	@Test
	public void saveDuplicated() throws RuntimeException {

		try {
			productService.save(product);

		} catch (BusinessException e) {
			Assert.assertTrue("Product Already Exists".equals(e.getMessage()));
			return;
		}

		Assert.assertFalse(true);
	}

	@Test
	public void findSuccess() throws RuntimeException {

		productService.find(123456);
		Assert.assertTrue(true);

	}

	@Test
	public void findError() throws RuntimeException {

		try {
			productService.find(654321);

		} catch (BusinessException e) {

			Assert.assertTrue("Product Invalid with the Sku informed".equals(e.getMessage()));
			return;
		}
		Assert.assertFalse(true);
	}

	@Test
	public void deleteSuccess() throws RuntimeException {
		try {
			Product obj = productService.find(123456);
			if (obj != null) {
				productService.delete(123456);
				Assert.assertTrue(true);
				return;
			}

		} catch (BusinessException e) {
			if ("Product Invalid with the Sku informed".equals(e.getMessage())) {
				Product product = criarProduto();
				productService.save(product);
				productService.delete(123456);
				Assert.assertTrue(true);
				return;

			}
		}

		Assert.assertFalse(true);
	}

	@Test
	public void deleteError() throws RuntimeException {
		try {
			productService.delete(654321);

		} catch (BusinessException e) {
			Assert.assertTrue("Product Invalid with the Sku informed".equals(e.getMessage()));
			return;
		}
	}

	private Product criarProduto() {

		Product product = new Product();
		product.setSku(123456);
		product.setName(java.util.UUID.randomUUID().toString());
		product.setInventory(criarInventario());

		return product;
	}

	private Inventory criarInventario() {

		Inventory inventory = new Inventory();
		inventory.setWarehouses(criarDeposito());

		return inventory;
	}

	private List<Warehouse> criarDeposito() {

		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		String randomString = java.util.UUID.randomUUID().toString();
		Warehouse warehouse = new Warehouse();
		warehouse.setLocality(randomString);
		warehouse.setType(randomString);
		warehouse.setQuantity(Integer.MIN_VALUE);

		warehouses.add(warehouse);

		warehouse = new Warehouse();
		warehouse.setLocality(randomString);
		warehouse.setType(randomString);
		warehouse.setQuantity(Integer.MIN_VALUE);

		warehouses.add(warehouse);

		return warehouses;

	}
}