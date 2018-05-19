package br.com.blz.testjava.repository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import br.com.blz.testjava.exception.ProductAlreadyRegisteredException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Product;

@Repository
public class ProductRepository {
	
	private Set<Product> products = new HashSet<>();

	public Integer save(Product product) throws ProductAlreadyRegisteredException{
		if(products.contains(product)){
			throw new ProductAlreadyRegisteredException("Product already registered");
		}
		products.add(product);
		return product.getSku();
	}
	
	public Integer update(Integer sku, Product newProduct) throws ProductNotFoundException{
		
		Product product = getProduct(sku);
		
		if(!products.contains(product)){
			throw new ProductNotFoundException("Product does not exist");
		}
		
		products.remove(product);
		products.add( new Product( sku , newProduct.getName(), newProduct.getInventory(), newProduct.isMarketable() ) );
		return sku;
	}
	
	public Integer delete(Integer sku) throws ProductNotFoundException{
		products.remove(getProduct(sku));
		return sku;
	}
	
	public Product findBySku(Integer sku) throws ProductNotFoundException{
		return getProduct(sku); 
	}
	
	private Product getProduct(Integer sku) throws ProductNotFoundException{
		for(Product product : products){
			if( product.getSku().equals(sku) ){
				return product;
			}
		}
		throw new ProductNotFoundException("Product does not exist");
	}
	
}
