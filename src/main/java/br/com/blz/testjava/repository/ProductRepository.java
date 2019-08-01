package br.com.blz.testjava.repository;

import br.com.blz.testjava.object.Product;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

   @Override
   List<Product> findAll();
}