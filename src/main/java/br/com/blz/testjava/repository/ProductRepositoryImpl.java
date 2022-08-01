package br.com.blz.testjava.repository;

import br.com.blz.testjava.exception.ProductAlreadyExistException;
import br.com.blz.testjava.model.Product;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Repository
public class ProductRepositoryImpl implements IProductRepository {

    private Map<Integer, Product> products = new ConcurrentHashMap<>();

    @Override
    public void insert(final Product product) {
        if(products.containsKey(product.getSku())){
            throw new ProductAlreadyExistException("Produto já cadastrado. Sku: "+ product.getSku());
        }

       products.put(product.getSku(), product);
    }

    @Override
    public void update(final Product product) {
        products.put(product.getSku(), product);
    }

    @Override
    public void delete(final Integer sku){
        products.remove(sku);
    }

    @Override
    public Product findBySku(final Integer sku){
        return products.get(sku);
    }
}
