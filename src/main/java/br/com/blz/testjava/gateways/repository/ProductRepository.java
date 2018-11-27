package br.com.blz.testjava.gateways.repository;

import br.com.blz.testjava.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findBySku(Integer sku);
}
