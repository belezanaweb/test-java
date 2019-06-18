package br.com.blz.testjava.repository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.DuplicatedEntityException;
import br.com.blz.testjava.exception.EntityNotFoundException;

@Component
public class ProductRepositoryImpl implements ProductRepository{
	
	public static Set<Product> products = new HashSet<Product>();

	@Override
	public Product save(Product product) throws DuplicatedEntityException{
		if(products.contains(product)) {
			throw new DuplicatedEntityException();
		}
		products.add(product);
		return product;
	}

	@Override
	public Product update(Product product)throws EntityNotFoundException{
		if(!products.contains(product)){
			throw new EntityNotFoundException();
		}
		products.add(product);
		return product;
	}

	@Override
	public void delete(Long sku) throws EntityNotFoundException{
		Boolean contains = Boolean.FALSE;
		for(Product product : products) {
			if(product.getSku().equals(sku)) {
				products.remove(product);
				contains = Boolean.TRUE;
				break;
			}
		}
		if(!contains) {
			throw new EntityNotFoundException();
		}
		
	}

	@Override
	public Product findBySku(Long sku) throws EntityNotFoundException{
		Product productFind = null;
		for(Product product : products) {
			if(product.getSku().equals(sku)) {
				productFind = product;
				break;
			}
		}
		if(productFind == null) {
			throw new EntityNotFoundException();
		}
		return productFind;
	}
	

}
