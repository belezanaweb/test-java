package br.com.blz.testjava.repository;

import br.com.blz.testjava.entity.ProductEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    ProductEntity findBySku(Long sku);

    @Modifying
    @Query("DELETE FROM ProductEntity WHERE sku = :sku")
    int deleteProductBySku(@Param("sku") Long sku);
}
