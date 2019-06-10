package br.com.blz.testjava.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.interfaces.IProductService;
import br.com.blz.testjava.repository.IProductRepository;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	private IProductRepository productRepository;
	
	private int quantity;
	
    public ProductService(){
 
    }

	@Override
    public void create(Product product) {
    	productRepository.save(product);
    }

	@Override
    public Boolean isExists(Long sku) {
    	return productRepository.existsBySku(sku);
    }

	@Override
	public void delete(Long sku) {
		productRepository.deleteBySky(sku);
	}

	@Override
	public Product reader(Long sku) {
		Product product = productRepository.findBySku(sku);

		if (product == null) {
			return null;
		}
		
		product.getInventory().getWarehouses().forEach(w -> {
			quantity += w.getQuantity();
		});
		product.getInventory().setQuantity(quantity);
		quantity = 0;
		return product;
	}
}