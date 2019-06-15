package br.com.blz.testjava.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.exception.ProductAlreadyException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.WareHouse;
import br.com.blz.testjava.model.WareHouseTypeEnum;

/**
 * Class responsible for testing product service methods.
 * 
 * @author Andre Barroso
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJavaProductServiceTests {

	/**
	 * Product service.
	 */
	@Autowired
	private ProductService service;
	
	/**
	 * Method responsible for testing product search with empty return with invalid sku.
	 */
	@Test
	public void findProducNotFoundTest() {
		
		Product product = this.createProduct(12L);

		this.service.createProduct(product);
		
		Product entityBase = this.service.findProduct(1L);
		
		assertTrue("Product not found when we inform a nonexistent sku.", entityBase == null);
	}

	/**
	 * Method responsible for testing product search with return ok with valid sku.
	 */
	@Test
	public void findProducFoundTest() {
		
		Product product = this.createProduct(123L);
		
		this.service.createProduct(product);
		
		Product entityCache = this.service.findProduct(123L);
		
		assertTrue("Product found when we inform a sku valid.", entityCache != null);
	}

	/**
	 * Method responsible for successfully testing product creation.
	 */
	@Test
	public void createProductWithSuccessTest() {
		
		Product product = this.createProduct(1234L);
		
		Product entityCache = this.service.createProduct(product);
		
		assertTrue("Product is created with succes when we inform a data valid.", entityCache != null);
	}

	/**
	 * Method responsible for testing product and product failure because the product already exists.
	 */
	@Test(expected = ProductAlreadyException.class)
	public void createProductWithErrorTest() {

		
		Product product = this.createProduct(12345L);
		this.service.createProduct(product);
		this.service.createProduct(product);
	}

	/**
	 * Method responsible for successfully testing the product update.
	 */
	@Test
	public void updateProductWithSuccessTest() {
		
		Product product = this.createProduct(123456L);
		
		this.service.createProduct(product);
		
		product.setName("Black Bean");
		
		boolean result = this.service.updateProduct(product);
		
		assertTrue("Product is updated with succes when we inform a data valid.", result);
	}

	/**
	 * Method responsible for testing product update failure because the product was not found.
	 */
	@Test(expected = ProductNotFoundException.class)
	public void updateProductWithErrorTest() {
		
		Product product = this.createProduct(1234567L);
		
		this.service.createProduct(product);
		
		Product product2 = this.createProduct(123458L);
		product2.setName("Black Bean");
		
		this.service.updateProduct(product2);
	}

	/**
	 * Method responsible for successfully deleting product.
	 */
	@Test
	public void deleteProductWithSuccessTest() {
		
		Product product = this.createProduct(12345679L);
		
		this.service.createProduct(product);

		boolean isDeleted = this.service.removeProduct(12345679L);
		
		assertTrue("Product is deleted with succes when we inform a sku valid.", isDeleted);
	}

	/**
	 * Method responsible for deleting product with error because product was not found.
	 */
	@Test(expected = ProductNotFoundException.class)
	public void deleteProductWithErrorTest() {
		
		this.service.removeProduct(1234567810L);
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
