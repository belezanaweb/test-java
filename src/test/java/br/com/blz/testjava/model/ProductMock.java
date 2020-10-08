package br.com.blz.testjava.model;

import java.util.ArrayList;

import br.com.blz.testjava.util.WarehouseType;

public class ProductMock {
	static private Long skuSequence = 0l;

	public static Product getNewProduct() { 
		skuSequence++;
		return new Product(skuSequence, "Product " + skuSequence, new Inventory(new ArrayList<>()));
	}
	
	public static Product getNewProductThatHasStockInTwoWarehouses() {
		Product product = getNewProduct();
		product.getInventory().getWarehouses().add(new Warehouse("Sao Paulo", 10L, WarehouseType.ECOMMERCE));
		product.getInventory().getWarehouses().add(new Warehouse("Rio de Janeiro", 5L, WarehouseType.PHYSICAL_STORE));
		return product;
	}
	
	public static Product getNewProductThatHasNoStockInTwoWarehouses() {
		Product product = getNewProduct();
		product.getInventory().getWarehouses().add(new Warehouse("Sao Paulo", 0L, WarehouseType.ECOMMERCE));
		product.getInventory().getWarehouses().add(new Warehouse("Rio de Janeiro", 0L, WarehouseType.PHYSICAL_STORE));
		return product;
	}
	
	public static Product getNewProductThatHasStockInOnlyOneofTwoWarehouses() {
		Product product = getNewProduct();
		product.getInventory().getWarehouses().add(new Warehouse("Sao Paulo", 10L, WarehouseType.ECOMMERCE));
		product.getInventory().getWarehouses().add(new Warehouse("Rio de Janeiro", 0L, WarehouseType.PHYSICAL_STORE));
		return product;
	}

	public static Product getNewProductWithoutId() {
		Product product = getNewProduct();
		product.setSku(null);
		return product;
	}
	
	public static Product getNewProductWithoutDescription() {
		Product product = getNewProduct();
		product.setDescription(null);
		return product;
	}
}
