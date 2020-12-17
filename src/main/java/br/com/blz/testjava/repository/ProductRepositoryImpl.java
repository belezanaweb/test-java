package br.com.blz.testjava.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.Product;

@Service
public class ProductRepositoryImpl implements ProductRepository {

    private static ProductRepositoryImpl instance;

    synchronized public static ProductRepositoryImpl getInstance() {
        if (instance == null)
            instance = new ProductRepositoryImpl();
        return instance;
    }

    private Map<Long, Product> db = new HashMap<Long, Product>();

    @Override
    public Optional<Product> getBySku(Long sku) {
        return Optional.ofNullable(db.get(sku));
    }

    @Override
    public Product save(Product product) {
        db.put(product.getSku(), product);
        return db.get(product.getSku());
    }

    @Override
    public Product update(Product product) {
        db.remove(product.getSku());
        db.put(product.getSku(), product);
        return db.get(product.getSku());
    }

    @Override
    public boolean delete(Long sku) {
        
        if(db.get(sku) == null)
            return false;
        
        db.remove(sku);
        
        return true;
    }
    
    
    public void drop() {
        this.db = new HashMap<Long, Product>();
    }

}
