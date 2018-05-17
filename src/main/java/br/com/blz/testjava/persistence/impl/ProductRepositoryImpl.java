package br.com.blz.testjava.persistence.impl;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.infra.NotFoundException;
import br.com.blz.testjava.infra.Storege;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.persistence.IProductRepository;

/**
 * <b>ProductRepository</b> is a {@link IProductRepository}'s implementation
 *  
 * @author Renan
 *
 */

@Component
public class ProductRepositoryImpl implements IProductRepository {
	
	private static final String PRODUTO = "PRODUTO";

	@Override
	public Product getBySku(Integer sku) {
		return Storege.products.stream()
				.filter(p -> p.getSku().equals(sku)).findFirst()
				.orElseThrow(() -> new NotFoundException(PRODUTO));
	}

	@Override
	public boolean insert(Product product) {
		 return Storege.products.add(product);
		 
	}

	@Override
	public boolean update(Product modifiedProduct) {
		Product product = getBySku(modifiedProduct.getSku());
		product.setInventory(modifiedProduct.getInventory());
		product.setName(modifiedProduct.getName());
		return true;
	}

	@Override
	public boolean remove(Integer sku) {
		return Storege.products.remove(getBySku(sku));
	}
	
	

}
