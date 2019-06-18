package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.DuplicatedEntityException;
import br.com.blz.testjava.exception.EntityNotFoundException;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product save(Product product) throws DuplicatedEntityException{
		product= productRepository.save(product);
		calculateQuantities(product);
		return product;
	}

	@Override
	public Product update(Product product) throws EntityNotFoundException{
		product= productRepository.update(product);
		calculateQuantities(product);
		return product;
	}

	@Override
	public void delete(Long sku) throws EntityNotFoundException{
		 productRepository.delete(sku);
	}

	@Override
	public Product findBySku(Long sku) throws EntityNotFoundException {
		Product product= productRepository.findBySku(sku);
		calculateQuantities(product);
		return product;
	}
	
	private void calculateQuantities(Product product) {
		Inventory inventory = product.getInventory();
		if(inventory.getWarehouses() != null) {
			inventory.setQuantity(inventory.getWarehouses().size());
			if(inventory.getQuantity().compareTo(0) >0) {
				product.setMarketable(Boolean.TRUE);
			}
		}
	}
	
}
