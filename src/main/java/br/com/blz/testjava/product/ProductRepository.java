package br.com.blz.testjava.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findBySku(Long sku);

    void deleteBySku(String sku);
}
