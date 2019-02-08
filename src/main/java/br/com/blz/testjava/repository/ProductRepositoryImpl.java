package br.com.blz.testjava.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.blz.testjava.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	
	private Map<Long, Product> mockDB = new HashMap<>();

	@Override
	public void create(Product p)  {
		this.mockDB.put(p.getSku(), p);
	}

	@Override
	public void update(Long id, Product p) {
		this.mockDB.put(id, p);
	}

	@Override
	public Product get(Long id) {
		return this.mockDB.get(id);
	}
	
	@Override
	public Collection<Product> getall() {
		return this.mockDB.values();
	}

	@Override
	public void delete(Long id) {
		this.mockDB.remove(id);
	}

	
	
	
	
	

}
