package br.com.blz.testjava;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.service.ProductService;

@SpringBootApplication(scanBasePackageClasses = ProductTest.class)
public class ProductTest {

	public static void main(String[] args) {
		SpringApplication.run(ProductTest.class, args);
	}

	@Autowired
	private ProductService productService;

	@Test
	public void save() {

		boolean success = false;

		try {
			Product product = criarProduto();
			productService.save(product);
			success = true;

		} catch (Exception e) {
			Assert.assertFalse(success);
		}

		Assert.assertTrue(success);
	}

	@Test
	public void saveDuplicated() throws RuntimeException {

		try {
			Product product = criarProduto();
			productService.save(product);

		} catch (Exception e) {
			Assert.assertTrue(e instanceof RuntimeException);
		}
	}

	private final Product criarProduto() {

		Product product = new Product();
		product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
		product.setSku(43264);
		product.setInventory(this.criarInventario());

		return product;
	}

	private final Inventory criarInventario() {

		Inventory inventory = new Inventory();
		inventory.setWarehouses(criarDeposito());

		return inventory;
	}

	private final List<Warehouse> criarDeposito() {

		List<Warehouse> warehouses = new ArrayList<>();

		Warehouse warehouse = new Warehouse();
		warehouse.setLocality("SP");
		warehouse.setQuantity(Integer.valueOf(12));
		warehouse.setType("ECOMMERCE");

		warehouses.add(warehouse);

		warehouse = new Warehouse();
		warehouse.setLocality("MOEMA");
		warehouse.setQuantity(Integer.valueOf(3));
		warehouse.setType("PHYSICAL_STORE");

		warehouses.add(warehouse);

		return warehouses;

	}
}