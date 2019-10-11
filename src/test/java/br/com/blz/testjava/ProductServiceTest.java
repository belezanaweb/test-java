package br.com.blz.testjava;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.WarehouseDTO;
import br.com.blz.testjava.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
	@Autowired
	private ProductService productService;

	@Test
	public void create() {
		ProductDTO product = new ProductDTO();
		WarehouseDTO warehouse1 = new WarehouseDTO();
		warehouse1.setLocality("SP");
		warehouse1.setQuantity(0L);
		warehouse1.setType("ECOMMERCE");
		WarehouseDTO warehouse2 = new WarehouseDTO();
		warehouse2.setLocality("MOEMA");
		warehouse2.setQuantity(0L);
		warehouse2.setType("PHYSICAL_STORE");
		InventoryDTO inventory = new InventoryDTO();
		inventory.getWarehouses().add(warehouse1);
		inventory.getWarehouses().add(warehouse2);
		product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
		product.setSku("43264");
		product.setInventory(inventory);
		ProductDTO productTemp = null;
		try {
			productService.delete("43264");
			productTemp = productService.create(product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(product, productTemp);
	}
	@Test
	public void update() {
		ProductDTO product = new ProductDTO();
		WarehouseDTO warehouse1 = new WarehouseDTO();
		warehouse1.setLocality("SP");
		warehouse1.setQuantity(6L);
		warehouse1.setType("ECOMMERCE");
		WarehouseDTO warehouse2 = new WarehouseDTO();
		warehouse2.setLocality("MOEMA");
		warehouse2.setQuantity(4L);
		warehouse2.setType("PHYSICAL_STORE");
		InventoryDTO inventory = new InventoryDTO();
		inventory.getWarehouses().add(warehouse1);
		inventory.getWarehouses().add(warehouse2);
		inventory.setQuantity(10L);
		product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 1000g");
		product.setSku("43264");
		product.setInventory(inventory);
		ProductDTO productTemp = null;
		try {
			create();
			productTemp = productService.update(product);
			System.out.println("update "+productTemp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(product, productTemp);
	}
	@Test
	public void get() {
		ProductDTO productTemp = null;
		update();
		try {
			productTemp = productService.search("43264");
			System.out.println("get "+productTemp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(productTemp.getIsMarketable());
	}
	@Test
	public void delete() {
		ProductDTO productTemp = null;
		try {
			productTemp = productService.delete("43264");
			productTemp = productService.search("43264");
			System.out.println("delete "+productTemp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertNull(productTemp);
	}
}
