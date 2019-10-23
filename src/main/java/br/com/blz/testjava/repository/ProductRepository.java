package br.com.blz.testjava.repository;

import br.com.blz.testjava.database.BelezaNaWebDatabase;
import br.com.blz.testjava.model.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductRepository {

    public Optional<Product> findById(final Long sku) {
        return BelezaNaWebDatabase.findById(sku);
    }

    public void save(final Product product) {
        BelezaNaWebDatabase.save(product);
    }

    public void deleteById(final Long sku) {
        BelezaNaWebDatabase.deleteById(sku);
    }
}
