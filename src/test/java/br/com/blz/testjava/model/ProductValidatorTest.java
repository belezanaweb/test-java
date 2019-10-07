package br.com.blz.testjava.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.blz.testjava.exception.SkuInvalidFormatException;

public class ProductValidatorTest {

	private ProductValidator validator;

	@Before
	public void setUp() {
		validator = new ProductValidator();
	}

	@Test(expected = SkuInvalidFormatException.class)
	public void testWithoutInventory() {
		Product product = Product.build().withSku(1l).withName("teste produto");
		validator.validate(product);
	}

	@Test(expected = SkuInvalidFormatException.class)
	public void testWithInventoryEmpty() {
		Product product = Product.build().withSku(1l).withName("teste produto").withInventory(Inventory.build());
		validator.validate(product);
	}

	@Test
	public void testWithInventoryEmptyListWarehouse() {
		Product product = Product.build().withSku(1l).withName("teste produto")
				.withInventory(Inventory.build().withWarehouse(new ArrayList<>()));
		validator.validate(product);
		assertEquals(Long.valueOf(0l), product.getInventory().getQuantity());
	}

	@Test
	public void testWithInventoryEmptySumOnlyOneWarehouse() {
		Product product = Product.build().withSku(1l).withName("teste produto")
				.withInventory(Inventory.build().withAddWarehouse(Warehouse.build().withLocality("SP")
						.withQuantity(Long.valueOf(3l)).withType(WarehouseType.ECOMMERCE)));
		validator.validate(product);
		assertEquals(Long.valueOf(3l), product.getInventory().getQuantity());
	}

	@Test
	public void testWithInventoryEmptySumTwoWarehouse() {
		Product product = Product.build().withSku(1l).withName("teste produto")
				.withInventory(Inventory.build()
						.withAddWarehouse(Warehouse.build().withLocality("SP").withQuantity(Long.valueOf(3l))
								.withType(WarehouseType.ECOMMERCE))
						.withAddWarehouse(Warehouse.build().withLocality("SP").withQuantity(Long.valueOf(2l))
								.withType(WarehouseType.PHYSICAL_STORE))
				);
		validator.validate(product);
		assertEquals(Long.valueOf(5l), product.getInventory().getQuantity());
	}

	@Test(expected = SkuInvalidFormatException.class)
	public void testWithInventoryEmptySumTwoWarehouseThrowsLocality() {
		Product product = Product.build().withSku(1l).withName("teste produto")
				.withInventory(Inventory.build()
						.withAddWarehouse(Warehouse.build().withLocality(null).withQuantity(Long.valueOf(3l))
								.withType(WarehouseType.ECOMMERCE))
						.withAddWarehouse(Warehouse.build().withLocality("SP").withQuantity(Long.valueOf(2l))
								.withType(WarehouseType.PHYSICAL_STORE))
				);
		validator.validate(product);
		assertEquals(Long.valueOf(5l), product.getInventory().getQuantity());
	}

	@Test(expected = SkuInvalidFormatException.class)
	public void testWithInventoryEmptySumTwoWarehouseThrowsType() {
		Product product = Product.build().withSku(1l).withName("teste produto").withInventory(Inventory.build()
				.withAddWarehouse(Warehouse.build().withLocality("SP").withQuantity(Long.valueOf(3l))
						.withType(WarehouseType.ECOMMERCE))
				.withAddWarehouse(Warehouse.build().withLocality("SP").withQuantity(Long.valueOf(2l)).withType(null))
		);
		validator.validate(product);
		assertEquals(Long.valueOf(5l), product.getInventory().getQuantity());
	}

	@Test
	public void testProductMarktable() {
		Product product = Product.build().withSku(1l).withName("teste produto")
				.withInventory(Inventory.build()
						.withAddWarehouse(Warehouse.build().withLocality("SP").withQuantity(Long.valueOf(3l))
								.withType(WarehouseType.ECOMMERCE))
						.withAddWarehouse(Warehouse.build().withLocality("MG").withQuantity(Long.valueOf(2l))
								.withType(WarehouseType.PHYSICAL_STORE))
				);
		validator.validate(product);
		assertEquals(Boolean.TRUE, product.isMarketable());
	}

	@Test
	public void testProductMarktableFalse() {
		Product product = Product.build().withSku(1l).withName("teste produto")
				.withInventory(Inventory.build()
						.withAddWarehouse(Warehouse.build().withLocality("SP").withQuantity(Long.valueOf(0l))
								.withType(WarehouseType.ECOMMERCE))
						.withAddWarehouse(Warehouse.build().withLocality("MG").withQuantity(Long.valueOf(0l))
								.withType(WarehouseType.ECOMMERCE))
				);
		validator.validate(product);
		assertEquals(Boolean.FALSE, product.isMarketable());
	}

}
