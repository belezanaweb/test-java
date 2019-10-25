package br.com.beleza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.beleza.model.Product;
import br.com.beleza.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Override
	public void save(Product product) {
		repository.save(product);
	}

	@Override
	public void update(Product product) {
		repository.update(product);
	}

	@Override
	public void delete(Product product) {
		repository.delete(product);
	}

	@Override
	public Product find(Integer sku) {
		return repository.find(sku);
	}

}
