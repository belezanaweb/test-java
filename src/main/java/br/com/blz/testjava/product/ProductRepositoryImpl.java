package br.com.blz.testjava.product;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	
	private static final Map<Long, Product> PRODUCTS = new HashMap<Long, Product>();
	
	static {
		Inventory inventory = new Inventory(null, Arrays.asList(new Warehouse("SP", 1L, Type.ECOMMERCE),
																new Warehouse("SP", 2L, Type.ECOMMERCE)));
		PRODUCTS.put(1L, new Product(1L, "Teste", inventory, true));
	}
	
	public Optional<Product> save(Product product) {
		return Optional.ofNullable(PRODUCTS.put(product.getSku(), product));
	}
	
	public Optional<Product> findBySku(Long sku) {
		return Optional.ofNullable(PRODUCTS.get(sku));
	}
	
	public Optional<Product> deleteBySku(Long sku) {
		return Optional.ofNullable(PRODUCTS.remove(sku));
	}
	
}
