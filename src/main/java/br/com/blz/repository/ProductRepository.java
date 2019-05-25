package br.com.blz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blz.models.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	
	ProductEntity findById(long sku);
	boolean existsById(long sku);
}
