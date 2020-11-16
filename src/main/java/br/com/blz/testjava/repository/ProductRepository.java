package br.com.blz.testjava.repository;

import br.com.blz.testjava.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
