/**
 * 
 */
package br.com.blz.testjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blz.testjava.model.Product;

/**
 * @author ocean
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
