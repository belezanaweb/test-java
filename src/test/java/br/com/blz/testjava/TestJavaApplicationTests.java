package br.com.blz.testjava;

import br.com.blz.testjava.exceptions.BusinessException;
import br.com.blz.testjava.models.Inventory;
import br.com.blz.testjava.models.Product;
import br.com.blz.testjava.models.Warehouse;
import br.com.blz.testjava.services.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestJavaApplicationTests {

	@InjectMocks
	private ProductService productService;
	private Product product1;
	private Product product2;
	private Product product3;
	private Product product4;

	@Before
	public void setup() {

		Warehouse warehouse1 = new Warehouse("SP", 7, "ECOMMERCE");
		Warehouse warehouse2 = new Warehouse("MOEMA", 3, "PHYSICAL_STORE");
		Warehouse warehouse3 = new Warehouse("SP", 5, "PHYSICAL_STORE");

		Inventory inventory1 = new Inventory(Arrays.asList(warehouse1, warehouse2));
		Inventory inventory2 = new Inventory(Arrays.asList(warehouse1, warehouse2, warehouse3));
		Inventory inventory4 = new Inventory();

		product1 = new Product(1L, "Produto 1", inventory1);
		product2 = new Product(1L, "Produto 1 Editado", inventory2);
		product3 = new Product(null, "Produto 3", inventory4);
		product4 = new Product(2L, "Produto 4", inventory4);


	}

	@Test
	public void createProductTest() {
		productService.save(this.product1);
	}

	@Test
	public void updateProductTest() {
		productService.save(this.product1);
		productService.update(this.product2);
		Assert.assertNotEquals(this.product1.getName(), productService.findOne(1L).getName());
	}

	@Test(expected = BusinessException.class)
	public void updateProductSkuInvalidTest() {
		productService.save(this.product3);
	}

	@Test(expected = BusinessException.class)
	public void updateProductDuplicatedTest() {
		productService.save(this.product1);
		productService.save(this.product1);
	}

	@Test
	public void getProductTest() {
		productService.save(this.product1);
		Assert.assertNotNull(productService.findOne(this.product1.getSku()));
	}

	@Test
	public void calculateQuantityTest() {
		productService.save(this.product1);
		Integer quantity = productService.findOne(this.product1.getSku()).getInventory().getQuantity();
		Assert.assertEquals(quantity, new Integer(10));

		productService.update(this.product2);
		Integer quantityAfter = productService.findOne(this.product2.getSku()).getInventory().getQuantity();
		Assert.assertEquals(quantityAfter, new Integer(15));
	}

	@Test
	public void isMarketableTest() {
		productService.save(this.product1);
		boolean marketable1 = productService.findOne(this.product1.getSku()).isMarketable();
		Assert.assertTrue(marketable1);

		productService.save(this.product4);
		boolean marketable2 = productService.findOne(this.product4.getSku()).isMarketable();
		Assert.assertFalse(marketable2);
	}

	@Test
	public void deleteProductTest() {
		productService.save(this.product1);
		productService.delete(this.product1.getSku());
		Assert.assertNull(this.productService.findOne(this.product1.getSku()));

	}

}
