/**
 * 
 */
package br.com.blz.testjava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.entity.ProductEntity;

/**
 * @author Augusto Lemes
 * @since 16/06/2019
 *
 */
@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

}
