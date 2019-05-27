package br.com.blz.testjava.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.blz.testjava.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
