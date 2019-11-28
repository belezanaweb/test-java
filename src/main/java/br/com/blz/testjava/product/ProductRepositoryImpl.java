package br.com.blz.testjava.product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	
	private static final Map<Long, Product> PRODUCTS = new HashMap<Long, Product>();
	
	@Override
	public Optional<Product> save(Product product) {
		try {
			PRODUCTS.put(product.getSku(), product);
			return Optional.of(product);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.empty();
	}
	
	@Override
	public Optional<Product> findBySku(Long sku) {
		return Optional.ofNullable(PRODUCTS.get(sku));
	}
	
	@Override
	public Boolean hasBySku(Long sku) {
		return PRODUCTS.containsKey(sku);
	}
	
	public Optional<Product> deleteBySku(Long sku) {
		return Optional.ofNullable(PRODUCTS.remove(sku));
	}
	
}
