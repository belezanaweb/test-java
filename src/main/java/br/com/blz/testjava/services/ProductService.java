package br.com.blz.testjava.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.blz.testjava.models.Product;
import br.com.blz.testjava.models.Warehouse;
import br.com.blz.testjava.repositories.ProductRepository;
import br.com.blz.testjava.repositories.WarehouseRepository;
import br.com.blz.testjava.services.exceptions.ProductServiceException;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private WarehouseRepository warehouseRepository;
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Transactional
	public Product save(Product product) {
		Product productSaved = productRepository.save(product);
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		for (Warehouse warehouse : product.getInventory().getWarehouses()) {
			warehouse.setInventoryId(productSaved.getInventory().getId());
			warehouses.add(warehouseRepository.save(warehouse));
		}
		productSaved.getInventory().setWarehouses(warehouses);
		return productSaved;
	}
	
	public Product findById(Long sku) {
		Product product = productRepository.findOne(sku);
		
		if (product == null) {
			throw new ProductServiceException("This product does not exist");
		}
		return product;
	}
	
	@Transactional
	public void delete(Long sku) {
		Product product = findById(sku);
		for (Warehouse warehouse : product.getInventory().getWarehouses())
			warehouseRepository.delete(warehouse);
		productRepository.delete(product);
	}
	
	@Transactional
	public Product update(Product product) {
		Product productSaved = productRepository.saveAndFlush(product);
		for (Warehouse warehouse : productSaved.getInventory().getWarehouses())
			warehouseRepository.delete(warehouse);
		warehouseRepository.flush();
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		for (Warehouse warehouse : product.getInventory().getWarehouses()) {
			warehouse.setInventoryId(product.getInventory().getId());
			warehouses.add(warehouseRepository.save(warehouse));
		}
		productSaved.getInventory().setWarehouses(warehouses);
		return productSaved;
	}
}