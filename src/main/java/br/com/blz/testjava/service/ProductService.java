/**
 * 
 */
package br.com.blz.testjava.service;

import java.util.List;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.exception.ProductException;
import br.com.blz.testjava.model.Product;

/**
 * The Interface ProductService.
 *
 * @author ocean
 */
public interface ProductService {
	
	/**
	 * Gets the products.
	 *
	 * @return the products
	 */
	List<ProductDTO> getProducts();

	/**
	 * Creates the.
	 *
	 * @param productDTO the product DTO
	 * @return the product
	 * @throws ProductException the product exception
	 */
	Product create(ProductDTO productDTO) throws ProductException;

	/**
	 * Update.
	 *
	 * @param productDTO the product DTO
	 * @return the product
	 * @throws ProductException the product exception
	 */
	Product update(ProductDTO productDTO) throws ProductException;

	/**
	 * Delete.
	 *
	 * @param sku the sku
	 * @throws ProductException the product exception
	 */
	void delete(Long sku) throws ProductException;

	/**
	 * Gets the product by.
	 *
	 * @param sku the sku
	 * @return the product by
	 * @throws ProductException the product exception
	 */
	ProductDTO getProductBy(Long sku) throws ProductException;

}
