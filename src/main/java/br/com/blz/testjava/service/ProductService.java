/**
 * 
 */
package br.com.blz.testjava.service;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.exception.ProductNotFoudException;

/**
 * @author Augusto Lemes
 * @since 16/06/2019
 *
 */
public interface ProductService {
	
	void save(ProductDTO productDTO);
	
	ProductDTO findOne(Long sku) throws ProductNotFoudException;
	
	Boolean delete(Long sku) throws ProductNotFoudException;

}
