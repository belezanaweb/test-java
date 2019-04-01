package br.com.blz.testjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
