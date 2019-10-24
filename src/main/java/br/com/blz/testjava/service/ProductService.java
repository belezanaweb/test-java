package br.com.blz.testjava.service;

import br.com.blz.testjava.models.Product;

public interface ProductService {

	public void save(Product product);

	public void update(Product product);

	public void delete(Integer sku);

	public Product find(Integer sku);
}