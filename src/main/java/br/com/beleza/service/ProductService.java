package br.com.beleza.service;

import br.com.beleza.model.Product;

public interface ProductService {

	void save(Product product);

	void update(Product product);
	
	void delete(Product product);
	
	Product find(Integer sku); 
}
