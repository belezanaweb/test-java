package br.com.blz.testjava.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findBySku(String sku);

    void deleteBySku(String sku);

//    void updateProduct(ProductEntity productEntity, String sku);
}
