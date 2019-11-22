package br.com.belezaNaWeb.javaTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.hibernate.validator.internal.constraintvalidators.bv.AssertTrueValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.belezaNaWeb.javaTest.domain.Inventory;
import br.com.belezaNaWeb.javaTest.domain.Product;
import br.com.belezaNaWeb.javaTest.domain.Warehouse;
import br.com.belezaNaWeb.javaTest.domain.WharehouseType;
import br.com.belezaNaWeb.javaTest.repository.ProductRepository;
import br.com.belezaNaWeb.javaTest.rest.ProductController;
import br.com.belezaNaWeb.javaTest.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJavaApplicationTests {
	
	@Autowired
	private ProductService productService;

	@Autowired
	public ProductController productController;

	@Test
	public void testFindBySku() {
		Product prod = productService.findProductBySku(8L);
		assertNotNull(prod);
		assertEquals(prod.getSku(), Long.valueOf(2));
	}

	@Test
	public void testFindAll() throws Exception {
		Product product = new Product(14L, "Product Test");
		ProductRepository repo = new ProductRepository();
		productService.createProduct(product);
		//boolean productCreated = repo.create(p);
		List<Product> prod = repo.findAll();
		assertNotNull(prod);
		assertEquals(prod.size(), 9);
		//assertEquals(productCreated, true);
	}

	@Test
	public void testCreate() {

		WharehouseType.ECOMMERCE.setDescription(WharehouseType.ECOMMERCE.getDescription());
		WharehouseType.PHYSICAL_STORE.setDescription(WharehouseType.PHYSICAL_STORE.getDescription());

		Product p = new Product();
		p = new Product(13L);
		p = new Product(13L, "Product Test");
		p.setSku(13L);
		Inventory i = new Inventory();
		i.setQuantity(10);
		i.equals(i);
		i.equals(new Product());
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		Warehouse w1 = new Warehouse();
		w1.equals(w1);
		w1 = new Warehouse("SP", 8, WharehouseType.ECOMMERCE.name());
		Warehouse w2 = new Warehouse();
		w2 = new Warehouse();
		w2.setLocality("SP");
		w2.setQuantity(13);
		w2.setType(WharehouseType.PHYSICAL_STORE.name());
		w2.equals(w1);
		warehouses.add(w1);
		warehouses.add(w2);
		i = new Inventory(warehouses);
		i.setWarehouses(warehouses);
		p.setInventory(i);
		ProductRepository repo = new ProductRepository();
		boolean productCreated = repo.create(p);
		p.toString();
		i.toString();
		w1.toString();
		assertNotNull(repo.findBySku(13L));
		assertEquals(productCreated, true);
		assertTrue(p.isMarketable());
		assertNotEquals(p.hashCode(), 0);
		assertNotEquals(i.hashCode(), 0);
		assertNotEquals(w1.hashCode(), 0);
	}

	@Test
	public void testUpdate() {
		Product product = new Product(26L, "Product Test");
		boolean productNew = productService.updateProduct(product);
		assertNotNull(productService.findProductBySku(26L));
		assertTrue(productNew);
	}

	@Test
	public void testDelete() throws Exception {
		Product prod = new Product(88L, "Product Test");
		productService.createProduct(prod);
		productService.deleteProduct(88L);
		assertNull(productService.findProductBySku(13L));
	}

}
