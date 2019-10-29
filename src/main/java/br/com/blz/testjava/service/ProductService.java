package br.com.blz.testjava.service;

import br.com.blz.testjava.entity.Product;

public interface ProductService {

	public Product save(Product product);

	public Product update(Product product);

	public void delete(Integer sku);

	public Product find(Integer sku);
}
