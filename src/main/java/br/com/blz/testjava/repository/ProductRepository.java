package br.com.blz.testjava.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.blz.testjava.model.Product;

@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProductRepository {
	private static Map<Long, Product> productRepo;
	private AtomicLong id;
	
	@Autowired
	public ProductRepository() {
		if(productRepo == null)
			productRepo = new HashMap();
		
		if(id == null)
			id = new AtomicLong(1);
	}
	
	public void insert(Product newProduct) {
		productRepo.put(id.getAndIncrement(), newProduct);
	}
}
