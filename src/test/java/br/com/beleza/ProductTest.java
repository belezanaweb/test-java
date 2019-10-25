package br.com.beleza;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.beleza.model.Inventory;
import br.com.beleza.model.Product;
import br.com.beleza.model.Warehouse;

public class ProductTest {

	;
	
	@Before
	public void before() {
	}
	
	@Test
	public void produtoQuantidadeZero() {

		Product product = new Product();
		product.setSku(1);
		product.setName("Product 1");
		product.setInventory(new Inventory());
		
		assertFalse(product.isMarketable());
		assertTrue(product.getInventory().getQuantity() == 0);
	}
	
	@Test
	public void produtoQuantidadeDiferenteZero() {

		Product product = new Product();
		product.setSku(1);
		product.setName("Product 1");
		Inventory inventory = new Inventory();
		inventory.addWarehouse(new Warehouse("SP", 1, "Body"));
		product.setInventory(inventory);
		assertTrue(product.isMarketable());
		assertTrue(product.getInventory().getQuantity() != 0);
	}
	
	@Test
	public void somaQuantideDoisWarehouseProduto() {

		Product product = new Product();
		product.setSku(1);
		product.setName("Product 1");
		Inventory inventory = new Inventory();
		inventory.addWarehouse(new Warehouse("SP", 1, "Body"));
		inventory.addWarehouse(new Warehouse("RJ", 10, "Body"));
		product.setInventory(inventory);
		assertTrue(product.isMarketable());
		assertTrue(product.getInventory().getQuantity() == 11);
	}
	
}
