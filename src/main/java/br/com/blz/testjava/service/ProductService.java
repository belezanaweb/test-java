package br.com.blz.testjava.service;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.DuplicatedEntityException;
import br.com.blz.testjava.exception.EntityNotFoundException;

public interface ProductService {
	
	public Product save(Product product)throws DuplicatedEntityException;
	
	public Product update(Product product)throws EntityNotFoundException;
	
	public void delete(Long sku)throws EntityNotFoundException;
	
	public Product findBySku(Long sku)throws EntityNotFoundException;
	
	

}
