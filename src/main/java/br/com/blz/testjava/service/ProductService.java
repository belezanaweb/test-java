package br.com.blz.testjava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dao.ProductDAO;
import br.com.blz.testjava.exception.ProductAlreadyExists;
import br.com.blz.testjava.exception.ProductNotFound;
import br.com.blz.testjava.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDAO productDao;

	public Product getBySku(Long sku) throws ProductNotFound {
		
		Product product = this.productDao.findById(sku);
		
		return product;
		
	}
	
	public List<Product> getAll() {
		
		return this.productDao.getAll();
	}

	public Product create(Product product) throws ProductAlreadyExists {
		
		return this.productDao.save(product);
	}
	
	public Product update(Long sku, Product product) throws ProductNotFound {
		
		return this.productDao.update(sku, product);
	}
	
	public void delete(Long sku) throws ProductNotFound {
		this.productDao.remove(sku);
	}

}
