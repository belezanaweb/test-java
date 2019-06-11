package br.com.blz.testjava.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.error.NoProductResultException;
import br.com.blz.testjava.error.ProductSavedException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Override
	public Product saveProduct(Product product) throws ProductSavedException {
		Product p = repository.findBySku(product.getSku());
		if(p != null) {
			throw new ProductSavedException("There is a product with sku " + product.getSku() + " saved.");
		}
		return repository.save(product);
	}

	@Override
	public Product getBySku(Long sku) {
		return repository.findBySku(sku);
	}

	@Override
	public Product update(Product product) throws NoProductResultException {
		Product p = repository.findBySku(product.getSku()); 
		if(p == null) {
			throw new NoProductResultException("No product with sku " + product.getSku());
		}
		return repository.update(product);
	}

	@Override
	public void deleteBySku(Long sku) {
		repository.delete(sku);

	}

}
