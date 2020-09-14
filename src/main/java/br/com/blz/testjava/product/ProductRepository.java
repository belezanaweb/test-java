package br.com.blz.testjava.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findBySku(Long sku);

    @Modifying(clearAutomatically = true)
    void deleteBySku(Long sku);

//    @Modifying(clearAutomatically = true)
//    @Query(value = "UPDATE product p SET p.* VALUES(:product) WHERE sku = :sku", nativeQuery = true)
//    Integer updateProduct(@Param("product") ProductEntity productEntity,
//                          @Param("sku") Long sku);
}
