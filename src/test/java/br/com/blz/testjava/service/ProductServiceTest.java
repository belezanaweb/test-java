package br.com.blz.testjava.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.domain.WarehouseType;
import br.com.blz.testjava.exception.DuplicateProductException;
import br.com.blz.testjava.exception.ProductNotFoundException;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceTest {
	
	@Autowired
	private ProductService productService;
	
	private Product product;
	
	@BeforeEach
	void setupUp() {
		product = new Product();
		product.setSku(1000);
		product.setName("product 1");
		
		Warehouse warehouse = new Warehouse();
		warehouse.setLocality("SP");
		warehouse.setQuantity(1);
		warehouse.setWarehouseType(WarehouseType.ECOMMERCE);
		
		Inventory inventory = new Inventory();
		inventory.setWarehouses(Lists.list(warehouse));
		
		product.setInventory(inventory);
	}
	
	@AfterEach
	void clearMapProducts() throws ProductNotFoundException {
		try{
			productService.delete(1000);
			productService.delete(1001);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	void save_ok() throws DuplicateProductException, ProductNotFoundException {
		productService.save(this.product);
		
		Product productSaved = productService.findBySku(product.getSku());
		
		assertEquals(product.getSku(), productSaved.getSku());
	}
	
	@Test
	void save_not_ok_duplicate_product() throws DuplicateProductException {
		assertThrows(DuplicateProductException.class, () -> {
			productService.save(this.product);
			productService.save(this.product);
		});
	}
	
	@Test
	void save_ok_n_products() throws DuplicateProductException {
		Product newProduct = new Product();
		newProduct.setSku(1001);
		newProduct.setName("product 2");
		
		Warehouse warehouse = new Warehouse();
		warehouse.setLocality("SP");
		warehouse.setQuantity(2);
		warehouse.setWarehouseType(WarehouseType.PHYSICAL_STORE);
		
		Inventory inventory = new Inventory();
		inventory.setWarehouses(Lists.list(warehouse));
		
		newProduct.setInventory(inventory);
		
		assertDoesNotThrow(() -> {
			productService.save(this.product);
			productService.save(newProduct);
		});
	}
	
	@Test
	void updateBySku_ok() throws DuplicateProductException, ProductNotFoundException {
		productService.save(this.product);
		
		this.product.setName("Product Updated Name");
		
		productService.updateBySku(1000, this.product);
		
		Product productUpdated = productService.findBySku(1000);
		
		assertEquals("Product Updated Name", productUpdated.getName());
	}
	
	@Test
	void updateBySku_not_ok_product_not_found() throws DuplicateProductException, ProductNotFoundException {
		assertThrows(ProductNotFoundException.class, () -> {
			productService.save(this.product);
			
			this.product.setName("Product Updated Name");
			
			productService.updateBySku(1001, this.product);
		});
	}
	
	@Test
	void findBySku_ok() throws DuplicateProductException, ProductNotFoundException {
		productService.save(this.product);
		
		Product productFound = productService.findBySku(1000);
		
		assertTrue(productFound != null);
	}
	
	@Test
	void findBySku_not_ok() throws ProductNotFoundException {
		assertThrows(ProductNotFoundException.class, () -> {
			productService.findBySku(1000);
		});
	}
	
	@Test
	void findBySku_ok_validate_inventory_quantity_more_0() throws DuplicateProductException, ProductNotFoundException {
		Integer quantity1SP = 10;
		Integer quantity1RJ = 15;
		
		Product productTestFind = new Product();
		productTestFind.setSku(1000);
		productTestFind.setName("product 2");
		
		Warehouse warehouseSP = new Warehouse();
		warehouseSP.setLocality("SP");
		warehouseSP.setQuantity(quantity1SP);
		warehouseSP.setWarehouseType(WarehouseType.PHYSICAL_STORE);
		
		Warehouse warehouseRJ = new Warehouse();
		warehouseRJ.setLocality("RJ");
		warehouseRJ.setQuantity(quantity1RJ);
		warehouseRJ.setWarehouseType(WarehouseType.PHYSICAL_STORE);
		
		Inventory inventory = new Inventory();
		inventory.setWarehouses(Lists.list(warehouseSP, warehouseRJ));
		
		productTestFind.setInventory(inventory);
		
		productService.save(productTestFind);
		
		Product productFound = productService.findBySku(1000);
		
		assertEquals(quantity1RJ + quantity1SP, productFound.getInventory().getQuantity());
	}

	@Test
	void findBySku_ok_validate_inventory_quantity_equal_0() throws DuplicateProductException, ProductNotFoundException {
		Integer quantity1SP = 0;
		Integer quantity1RJ = 0;
		
		Product productTestFind = new Product();
		productTestFind.setSku(1000);
		productTestFind.setName("product 2");
		
		Warehouse warehouseSP = new Warehouse();
		warehouseSP.setLocality("SP");
		warehouseSP.setQuantity(quantity1SP);
		warehouseSP.setWarehouseType(WarehouseType.PHYSICAL_STORE);
		
		Warehouse warehouseRJ = new Warehouse();
		warehouseRJ.setLocality("RJ");
		warehouseRJ.setQuantity(quantity1RJ);
		warehouseRJ.setWarehouseType(WarehouseType.PHYSICAL_STORE);
		
		Inventory inventory = new Inventory();
		inventory.setWarehouses(Lists.list(warehouseSP, warehouseRJ));
		
		productTestFind.setInventory(inventory);
		
		productService.save(productTestFind);
		
		Product productFound = productService.findBySku(1000);
		
		assertEquals(quantity1RJ + quantity1SP, productFound.getInventory().getQuantity());
	}
	
	@Test
	void findBySku_ok_validate_marketable_true() throws DuplicateProductException, ProductNotFoundException {
		Integer quantity1SP = 10;
		Integer quantity1RJ = 15;
		
		Product productTestFind = new Product();
		productTestFind.setSku(1000);
		productTestFind.setName("product 2");
		
		Warehouse warehouseSP = new Warehouse();
		warehouseSP.setLocality("SP");
		warehouseSP.setQuantity(quantity1SP);
		warehouseSP.setWarehouseType(WarehouseType.PHYSICAL_STORE);
		
		Warehouse warehouseRJ = new Warehouse();
		warehouseRJ.setLocality("RJ");
		warehouseRJ.setQuantity(quantity1RJ);
		warehouseRJ.setWarehouseType(WarehouseType.PHYSICAL_STORE);
		
		Inventory inventory = new Inventory();
		inventory.setWarehouses(Lists.list(warehouseSP, warehouseRJ));
		
		productTestFind.setInventory(inventory);
		
		productService.save(productTestFind);
		
		Product productFound = productService.findBySku(1000);
		
		assertTrue(productFound.isMarketable());
	}
	
	@Test
	void findBySku_ok_validate_marketable_false() throws DuplicateProductException, ProductNotFoundException {
		Integer quantity1SP = 0;
		Integer quantity1RJ = 0;
		
		Product productTestFind = new Product();
		productTestFind.setSku(1000);
		productTestFind.setName("product 2");
		
		Warehouse warehouseSP = new Warehouse();
		warehouseSP.setLocality("SP");
		warehouseSP.setQuantity(quantity1SP);
		warehouseSP.setWarehouseType(WarehouseType.PHYSICAL_STORE);
		
		Warehouse warehouseRJ = new Warehouse();
		warehouseRJ.setLocality("RJ");
		warehouseRJ.setQuantity(quantity1RJ);
		warehouseRJ.setWarehouseType(WarehouseType.PHYSICAL_STORE);
		
		Inventory inventory = new Inventory();
		inventory.setWarehouses(Lists.list(warehouseSP, warehouseRJ));
		
		productTestFind.setInventory(inventory);
		
		productService.save(productTestFind);
		
		Product productFound = productService.findBySku(1000);
		
		assertTrue(!productFound.isMarketable());
	}
	
}
