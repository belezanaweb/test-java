package br.com.beleza.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.beleza.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private Map<Integer, Product> products = new HashMap<>();
	
	@Override
	public void save(Product product) {
		if(products.containsKey(product.getSku())) {
			throw new ProductAlreadyExistException("Dois produtos são considerados iguais se os seus skus forem iguais");
		}
		products.put(product.getSku(), product);
	}

	@Override
	public void update(Product product) {
		products.replace(product.getSku(), product);
	}

	@Override
	public void delete(Product product) {
		products.remove(product.getSku());
	}

	@Override
	public Product find(Integer sku) {
		
		Product p = products.get(sku);
		
		if(p == null) {
			throw new ProductNotFoundException("Product not found "+sku);
		}
		return p;
	}
}
