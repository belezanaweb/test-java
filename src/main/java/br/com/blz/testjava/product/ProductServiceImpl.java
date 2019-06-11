package br.com.blz.testjava.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Override
	public Product saveProduct(Product product) {
		Product p = repository.findBySku(product.getSku());
		if(p != null) {
			//lanca exe
		}
		return repository.save(product);
	}

	@Override
	public Product getBySku(Long sku) {
		return repository.findBySku(sku);
	}

	@Override
	public Product update(Product product) {
		return repository.update(product);
	}

	@Override
	public void deleteBySku(Long sku) {
		repository.delete(sku);

	}

}
