package br.com.blz.testjava.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.*;

import br.com.blz.testjava.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

    Optional<Product> findBySku(String Sku);
    Optional<Product> deleteBySku(String sku);

}
