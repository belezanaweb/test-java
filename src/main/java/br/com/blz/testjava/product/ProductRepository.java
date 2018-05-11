package br.com.blz.testjava.product;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import br.com.blz.testjava.model.Product;

final class ProductRepository {
	
	private final  Set<Product> products = new HashSet<>();
	public static final ProductRepository INSTANCE = new ProductRepository();
	
	private ProductRepository(){
		
	}

	public  Optional<Product> findBySku(final Integer sku){
		return products.stream()
				.filter(product -> product.getSku().equals(sku))
				.findFirst();
	}
	
	public void saveProduct(final Product product){
		products.add(product);
	}
	
	public void deleteProduct( final int sku){
		products.removeIf(product -> product.getSku() == sku);
	}
	
	
	
	

}
