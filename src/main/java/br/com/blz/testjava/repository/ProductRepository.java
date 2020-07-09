package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> { }
