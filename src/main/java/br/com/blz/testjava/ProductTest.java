package br.com.blz.testjava;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
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
public class ProductTest {

	@Autowired
	private ProductService productService;

	@Test
	public void saveSuccess() {

		Product product = criarProduto();
		productService.save(product);
		Assert.assertTrue(true);
	}

	@Test
	public void saveDuplicated() throws RuntimeException {

		try {
			Product product = criarProduto();
			productService.save(product);

		} catch (BusinessException e) {
			Assert.assertTrue("O produto já existe cadastrado.".equals(e.getMessage()));
			return;
		}

		Assert.assertFalse(true);
	}

	@Test
	public void findSuccess() throws RuntimeException {

		productService.find(43264);
		Assert.assertTrue(true);

	}

	@Test
	public void findError() throws RuntimeException {

		try {
			productService.find(987890);

		} catch (BusinessException e) {

			Assert.assertTrue("O produto informado não existe cadastrado.".equals(e.getMessage()));
			return;
		}
		Assert.assertFalse(true);
	}

	@Test
	public void deleteSuccess() throws RuntimeException {
		try {
			Product obj = productService.find(43264);
			if (obj != null) {
				productService.delete(43264);
				Assert.assertTrue(true);
				return;
			}

		} catch (BusinessException e) {
			if ("O produto informado não existe cadastrado.".equals(e.getMessage())) {
				Product product = criarProduto();
				productService.save(product);
				productService.delete(43264);
				Assert.assertTrue(true);
				return;

			}
		}

		Assert.assertFalse(true);
	}

	@Test
	public void deleteError() throws RuntimeException {
		try {
			productService.delete(12345);

		} catch (BusinessException e) {
			Assert.assertTrue("O produto informado não existe cadastrado.".equals(e.getMessage()));
			return;
		}
	}

	private Product criarProduto() {

		Product product = new Product();
		product.setSku(43264);
		product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
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

		Warehouse warehouse = new Warehouse();
		warehouse.setLocality("SP");
		warehouse.setType("ECOMMERCE");
		warehouse.setQuantity(12);

		warehouses.add(warehouse);

		warehouse = new Warehouse();
		warehouse.setLocality("MOEMA");
		warehouse.setType("PHYSICAL_STORE");
		warehouse.setQuantity(3);

		warehouses.add(warehouse);

		return warehouses;

	}
}