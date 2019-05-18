package br.com.blz.testjava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.model.Product;


@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

}
