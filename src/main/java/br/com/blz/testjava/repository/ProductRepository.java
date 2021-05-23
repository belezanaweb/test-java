package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends InMemoryRepository<Product, Long> {

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }

    @Override
    <S extends Product> Long getEntityId(S product) {
        return product.getSku();
    }
}
