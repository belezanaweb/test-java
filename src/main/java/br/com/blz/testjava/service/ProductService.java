package br.com.blz.testjava.service;

import br.com.blz.testjava.model.entity.Product;
import java.util.List;
import java.util.Optional;
import javax.validation.ValidationException;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> find(Long sku);

    Boolean delete(Long sku);

    Product save(Product product);

    Product update(Product product) throws ValidationException;
}
