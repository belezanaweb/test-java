package br.com.blz.testjava.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.blz.testjava.model.Product;

@Repository
@Transactional
public class ProductDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public Product find(Integer id) {
		return manager.find(Product.class, id);
	}

	public Product save(Product product) {
		manager.persist(product);
		return product;
	}

	public Product update(Product product) {
		return manager.merge(product);
	}
	
	public void delete(Product product) {
		manager.remove(product);
	}

}
