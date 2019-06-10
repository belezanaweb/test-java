package br.com.blz.testjava.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.entity.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
	Boolean existsBySku(Long sku);
	
	Product findBySku(Long sku);

    @Modifying
    @Transactional
    @Query("delete from Product u where u.sku = ?1")
    void deleteBySky(Long sku);
}