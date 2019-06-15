package br.com.blz.testjava.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.exception.ProductAlreadyException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.WareHouse;
import br.com.blz.testjava.repository.ProductRepository;

/**
 * Class of service responsible for processing customer requisitions by searching, 
 * creating, updating and removing products in the system.
 * 
 * @author Andre Barroso
 *
 */
@Service
public class ProductService {

	/**
	 * Logger of service.
	 */
	Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository repository;
	
	/**
	 * Method responsible for fetching a product through the sku.
	 * 
	 * @param sku Product sku
	 * @return Entity base.
	 */
	public Product findProduct(Long sku) {
		
		logger.info("Searching the product with the SKU: " + sku);
		
		Product entityBase = this.repository.findBySku(sku);
		
		if(entityBase != null) {
			logger.info("Product " + sku + " found!");
		} else {
			logger.info("Product " + sku + " not found!");
		}
		return entityBase;
	}
	
	/**
	 * Method responsible for creating a new product.
	 * 
	 * @param product New Product
	 * @return Entity base
	 */
	public Product createProduct(Product product) {
		
		Product entityBase = this.repository.findBySku(product.getSku());

		if(entityBase != null) {
			logger.error("Product " + product.getSku() + " already registered!");
			throw new ProductAlreadyException("Product already registered!");
		}
		
		logger.info("Creating the product with the SKU: " + product.getSku());
		
		this.repository.save(product);

		logger.info("Product " + product.getSku() + " created!");
		
		return product;
	}

	/**
	 * Method responsible for updating a product.
	 * 
	 * @param product Entity base
	 * @return Result.
	 */
	public boolean updateProduct(Product product) {
		
		Product entityBase = this.repository.findBySku(product.getSku());

		if(entityBase == null) {
			logger.error("Product " + product.getSku() + " not found!");
			throw new ProductNotFoundException("Product not found to update!");
		}
		
		logger.info("Updating the product with the SKU: " + product.getSku());
		
		this.prepareProductToUpdate(entityBase, product);
		
		this.repository.save(entityBase);
		
		logger.info("Product " + product.getSku() + " updated!");
		
		return true;
	}

	/**
	 * Method responsible for remove a product by sku.
	 * 
	 * @param sku Product sku
	 * @return Result.
	 */
	public boolean removeProduct(Long sku) {
		
		Product entityBase = this.repository.findBySku(sku);

		if(entityBase != null) {
			logger.info("Removing the product with the SKU: " + sku);
			
			this.repository.delete(entityBase);
			
			logger.info("Product " + sku + " removed!");
		} else {
			logger.error("Product " + sku + " not found!");
			throw new ProductNotFoundException("Product not found to delete!");
		}
		return true;
	}
	
	/**
	 * Method responsible for preparing a product to be updated.
	 * 
	 * @param entityBase Entity base
	 * @param product Produt to update
	 */
	private void prepareProductToUpdate(Product entityBase, Product product) {

		entityBase.setName(product.getName());
		
		// If collection is empty return.
		if(product.getInventory() == null || product.getInventory().getWareHouses() == null || product.getInventory().getWareHouses().isEmpty()) {
			return;
		}
		
		// If entity base collection is empty input new collection
		if(entityBase.getInventory() == null || entityBase.getInventory().getWareHouses() == null || entityBase.getInventory().getWareHouses().isEmpty()) {
			
			if(entityBase.getInventory() == null) {
				entityBase.setInventory(new Inventory());
			}
			entityBase.getInventory().setWareHouses(product.getInventory().getWareHouses());
		} else {
			
			// Update if WH exists or insert.
			product.getInventory().getWareHouses().forEach( pWh -> {
				
				Optional<WareHouse> wh = entityBase.getInventory().getWareHouses().stream().filter( eWh -> {
					
					return eWh.getLocality() != null && eWh.getLocality().equalsIgnoreCase(pWh.getLocality()) 
							&& eWh.getType() != null && eWh.getType().equals(pWh.getType());
				}).findFirst();
				
				wh.ifPresent( w -> w.setQuantity(pWh.getQuantity()) );
				
				if(!wh.isPresent()) {
					entityBase.getInventory().getWareHouses().add(pWh);
				}
			});
		}
	}
	
}
