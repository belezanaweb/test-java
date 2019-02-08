package br.com.blz.testjava.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public void create(Product p) {
		Product productFromDB = repository.get(p.getSku());
		if(productFromDB !=null && productFromDB.equals(p)) {
			throw new ProductAlreadyExistsException(String.format("Produto SKU: %d already exists!", p.getSku()));
		}
		repository.create(p);
	}

	public void update(Product p) {
		repository.update(p.getSku(), p);
	}

	public Collection<Product>getAll() {
		return repository.getall();
	}
	
	public Product get(Long id) {
		return repository.get(id);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

}
