package br.com.blz.testjava.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.model.Product;

@Repository
public class ProductRepository implements ProductRepositoryInterface {

	private List<Product> products = new ArrayList<Product>();

	@Override
	public Product save(Product product) {
		if(this.findBySku(product.getSku()) != null)
			throw new ProductAlreadyExistsException("Product already stored");
		
		this.products.add(product);
		return product;
	}

	@Override
	public Product findBySku(Long sku) {
		List<Product> products = this.products.stream().filter(p -> sku.equals(p.getSku())).collect(Collectors.toList());
		if(products.size() == 0)
			return null;
			
		return products.get(0);
		
	}

	@Override
	public void deleteBySku(Long sku) {
		Product product = this.findBySku(sku);
		if(product == null) 
			return;
		
		this.products.remove(product);
	}

	@Override
	public List<Product> findAll() {
		return this.products;
	}
}
