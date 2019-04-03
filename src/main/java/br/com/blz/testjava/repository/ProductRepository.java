package br.com.blz.testjava.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.model.Product;

@Component
public class ProductRepository implements Repository<Product, Long> {

	private List<Product> data = new ArrayList<Product>();
	
	public ProductRepository() {
		for(int i=0; i< 5; i++) {
			data.add(new Product(Long.valueOf(i), "Produto_" + i));
		}
	}
	
	@Override
	public List<Product> findAll() {
		return Collections.unmodifiableList(this.data);
	}
	
	@Override
	public Product findById(Long key) {
		
		if (data.isEmpty()) return null;
		
		List<Product> products = this.data.stream().filter(prod -> {
			return prod.equals(new Product(key));
		}).collect(Collectors.toList());
		
		return products != null && !products.isEmpty() ? products.get(0) : null;
		
	}
	
	@Override
	public void create(Product product) {
		if (product == null) throw new IllegalArgumentException("Product must be informed");
		this.data.add(product);
	}
	
	@Override
	public void update(Product entity) {
		Product foundProduct = this.findById(entity.getSku());
		foundProduct.setName(entity.getName());		
	}
	
	@Override
	public void delete(Product product) {
		if (product != null) this.data.remove(product);
	}
	
}
