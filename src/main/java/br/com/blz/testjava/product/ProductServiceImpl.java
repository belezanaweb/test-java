package br.com.blz.testjava.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.error.NoProductResultException;
import br.com.blz.testjava.error.ProductSavedException;
import br.com.blz.testjava.warehouse.Warehouse;

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
		product = repository.save(product);
		product = setInventoryQuantity(product);	
		product = isMarketable(product);
		return product;
	}

	@Override
	public Product getBySku(Long sku) {
		Product product = repository.findBySku(sku);
		if(product != null) {
			product = setInventoryQuantity(product);	
			product = isMarketable(product);
		}
		return product;
	}

	@Override
	public Product update(Product product) throws NoProductResultException {
		Product p = repository.findBySku(product.getSku()); 
		if(p == null) {
			throw new NoProductResultException("No product with sku " + product.getSku());
		}
		p = repository.update(product);
		p = setInventoryQuantity(product);	
		p = isMarketable(product);
		return p;
	}

	@Override
	public void deleteBySku(Long sku) throws NoProductResultException {
		Product p = repository.findBySku(sku); 
		if(p == null) {
			throw new NoProductResultException("No product with sku " + sku);
		}
		repository.delete(sku);

	}
	
	private Product setInventoryQuantity(Product product) {
		Integer quantity = 0;
		List<Warehouse> warehouses = product.getInventory().getWarehouses();
		
		for (Warehouse warehouse : warehouses) {
			quantity += warehouse.getQuantity();
		}
		
		product.getInventory().setQuantity(quantity);
		
		return product;
	}
	
	private Product isMarketable(Product product) {
		Integer inventoryQuantity = product.getInventory().getQuantity();
		boolean isMarketable = false;
		if(inventoryQuantity > 0) {
			isMarketable = true;
		}
		
		product.setIsMarketable(isMarketable);
		
		return product;
	}

}
