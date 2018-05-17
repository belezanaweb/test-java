package br.com.blz.testjava.persistence;

import br.com.blz.testjava.model.Product;

/**
 * <b>IProductRepository</b> is the interface that provides<br/> 
 * the services of storing, querying, changing and removing {@link Product} 
 * 
 * @author Renan.Farias
 *
 */

public interface IProductRepository {
	
	/**
	 * Recovers a product by your sku 
	 * @param sku
	 * @return product
	 */
	Product getBySku(Integer sku);
	
	/**
	 * This method is responsible for storing a product.
	 * @param {@link product}
	 * @return a boolean that represents if the product was stored or not.
	 */
	boolean insert(Product product);
	
	/**
	 * This method is responsible for changing the product.
	 * @param {@link product}
	 * @return a boolean that represents if the product was update or not.
	 */
	boolean update(Product product);
	
	/**
	 * This method is responsible for removing the {@link product} identified by your sku.
	 * 
	 * @param sku
	 * @return a boolean that represents if the product was removed or not.
	 */
	boolean remove(Integer sku);
}
