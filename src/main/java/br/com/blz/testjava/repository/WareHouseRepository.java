/**
 * 
 */
package br.com.blz.testjava.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.entity.ProductEntity;
import br.com.blz.testjava.entity.WareHouseEntity;

/**
 * @author Augusto Lemes
 * @since 16/06/2019
 *
 */
@Repository
public interface WareHouseRepository extends CrudRepository<WareHouseEntity, String>{

	List<WareHouseEntity> findByProduct(ProductEntity product);
	
	
}
