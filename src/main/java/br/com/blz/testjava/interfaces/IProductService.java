package br.com.blz.testjava.interfaces;

import br.com.blz.testjava.entity.Product;

public interface IProductService {
	public void create(Product product);
	
	public Boolean isExists(Long sku);
	
	public void delete(Long sku);

	public Product reader(Long sku);
}