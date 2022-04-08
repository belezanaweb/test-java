package br.com.blz.testjava.infrastructure.repository.product;

import br.com.blz.testjava.infrastructure.config.DbConfig;
import br.com.blz.testjava.product.Product;
import br.com.blz.testjava.product.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DbRepositoryProduct implements ProductRepository {
    private DbConfig dataBaseChoice;

    public DbRepositoryProduct(DbConfig dataBaseChoice) {
        this.dataBaseChoice = dataBaseChoice;
    }

    @Override
    public Product saveProduct(Product product) {
        return dataBaseChoice.saveProductDb(product);
    }

    @Override
    public Optional<Product> findBySkuProduct(Long sku) {
        return dataBaseChoice.findBySkuProductDb(sku);
    }

    @Override
    public List<Product> findAllProducts() {
        return dataBaseChoice.findAllProductsDb();
    }

    @Override
    public void updateProduct(Product product) { dataBaseChoice.saveProductDb(product);}

    @Override
    public void deleteProduct(Long sku) {
        dataBaseChoice.deleteProductDb(sku);
    }
}
