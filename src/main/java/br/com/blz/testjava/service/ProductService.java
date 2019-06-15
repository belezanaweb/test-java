package br.com.blz.testjava.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.exception.ProductAlreadyException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.WareHouse;

/**
 * Class of service responsible for processing customer requisitions by searching, 
 * creating, updating and removing products in the system.
 * 
 * @author Andre Barroso
 *
 */
@Service
public class ProductService {

	Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	/**
	 * Product list cache.
	 */
	private Map<Long, Product> products = new HashMap<>();
	
	/**
	 * Method responsible for fetching a product through the sku.
	 * 
	 * @param sku Product sku
	 * @return Entity base.
	 */
	public Product findProduct(Long sku) {
		
		logger.info("Searching the product with the SKU: " + sku);
		
		Product product = null;
		Optional<Entry<Long, Product>> opProduct = this.getProducts().entrySet().stream().filter( p -> p.getKey().equals(sku)).findFirst();
		
		if(opProduct.isPresent()) {
			product = opProduct.get().getValue();
			logger.info("Product " + sku + " found!");
		} else {
			logger.info("Product " + sku + " not found!");
		}
		return product;
	}
	
	/**
	 * Method responsible for creating a new product.
	 * 
	 * @param product New Product
	 * @return Entity base
	 */
	public Product createProduct(Product product) {
		
		Optional<Entry<Long, Product>> opProduct = this.getProducts().entrySet().stream().filter( p -> p.getKey().equals(product.getSku())).findFirst();

		opProduct.ifPresent( p -> {
			logger.error("Product " + product.getSku() + " already registered!");
			throw new ProductAlreadyException("Product already registered!");
		});
		
		logger.info("Creating the product with the SKU: " + product.getSku());
		
		this.getProducts().put(product.getSku(), product);

		logger.info("Product " + product.getSku() + " created!");
		
		return product;
	}

	/**
	 * Method responsible for updating a product.
	 * 
	 * @param product Entity base
	 * @return Entity base updated
	 */
	public Product updateProduct(Product product) {
		
		Optional<Entry<Long, Product>> opProduct = this.getProducts().entrySet().stream().filter( p -> p.getKey().equals(product.getSku())).findFirst();

		if(!opProduct.isPresent()) {
			logger.error("Product " + product.getSku() + " not found!");
			throw new ProductNotFoundException("Product not found to update!");
		}
		
		logger.info("Updating the product with the SKU: " + product.getSku());
		
		Product entityCache = opProduct.get().getValue();
		
		this.prepareProductToUpdate(entityCache, product);
		
		logger.info("Product " + product.getSku() + " updated!");
		
		return entityCache;
	}

	/**
	 * Method responsible for remove a product by sku.
	 * 
	 * @param sku Product sku
	 * @return Result.
	 */
	public boolean removeProduct(Long sku) {
		
		Optional<Entry<Long, Product>> opProduct = this.getProducts().entrySet().stream().filter( p -> p.getKey().equals(sku)).findFirst();

		opProduct.ifPresent( p -> {
			logger.info("Removing the product with the SKU: " + sku);
			
			this.getProducts().remove(sku);
			
			logger.info("Product " + sku + " removed!");
		});

		if(!opProduct.isPresent()) {
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
	
	/**
	 * Cached list of products.
	 * 
	 * @return Product list.
	 */
	@Cacheable
	private Map<Long, Product> getProducts() {
		return this.products;
	}

}
