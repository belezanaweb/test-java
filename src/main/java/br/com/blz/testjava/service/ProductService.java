package br.com.blz.testjava.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

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
		
		Product product = null;
		Optional<Entry<Long, Product>> opProduct = this.getProducts().entrySet().stream().filter( p -> p.getKey().equals(sku)).findFirst();
		
		if(opProduct.isPresent()) {
			product = opProduct.get().getValue();
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
			throw new ProductAlreadyException("Product already registered!");
		});
		
		this.getProducts().put(product.getSku(), product);

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
			throw new ProductNotFoundException("Product not found to update!");
		}
		
		Product entityCache = opProduct.get().getValue();
		
		this.prepareProductToUpdate(entityCache, product);
		
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
			this.getProducts().remove(sku);
		});

		if(!opProduct.isPresent()) {
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
