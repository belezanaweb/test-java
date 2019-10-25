package br.com.beleza.repository;

import br.com.beleza.model.Product;

public interface ProductRepository {
	
	void save(Product product);

	void update(Product product);
	
	void delete(Product product);
	
	Product find(Integer sku); 
}
