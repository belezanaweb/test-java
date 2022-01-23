package br.com.blz.testjava.data.repositories;

import br.com.blz.testjava.domain.entities.Product;
import br.com.blz.testjava.domain.repositories.IProductRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl  implements IProductRepository{

	private List<Product> productsDB;
	
	public ProductRepositoryImpl() {
		super();
		this.productsDB = new ArrayList<Product>();
	}

	@Override
	public String add(Product product) {
		this.productsDB.add(product);
		return product.getSku();
	}

	@Override
	public String update(Product product, String sku) {
		Product productToUpdate = this.find(sku);
		int productIndex = this.productsDB.indexOf(productToUpdate);
		this.productsDB.set(productIndex, product);
        return product.getSku();      
                
	}

	@Override
	public Boolean delete(String sku) {
		return (this.productsDB.removeIf(prd -> prd.getSku().equals(sku))) ? true : false;
	}

	@Override
	public List<Product> findAll() {
		return this.productsDB;
	}

	@Override
	public Product find(String sku) {
		return this.productsDB.stream()
                .filter(prd -> prd.getSku().equals(sku))
                .findFirst()
                .orElse(null);
	}


	
}
