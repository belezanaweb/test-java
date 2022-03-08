package br.com.blz.testjava.repository.Impl;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.repository.IProductRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepositoryMemoryImpl implements IProductRepository {

    Map<Integer, Product> products = new HashMap<>();

    @Override
    public void save(Product product) {
        this.products.put(product.getSku(), product);
    }

    @Override
    public Product edit(Product product) {
        this.products.put(product.getSku(), product);
        return this.get(product.getSku());
    }

    @Override
    public Product get(int productSku) {
        return this.products.get(productSku);
    }

    @Override
    public void delete(int productSku) {
        this.products.remove(productSku);
    }
}
