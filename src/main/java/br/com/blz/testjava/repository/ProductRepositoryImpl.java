package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    private final Map<Integer,ProductEntity> products = new ConcurrentHashMap<>();

    @Override
    public void createProduct(ProductEntity productEntity) {
        products.put(productEntity.getSku(),productEntity);
    }

    @Override
    public void updateProduct(final ProductEntity productModel) {
        products.put(productModel.getSku(), productModel);
    }

    @Override
    public void deleteProduct(final Integer sku){
        products.remove(sku);
    }

    @Override
    public ProductEntity findProductBySku(final Integer sku){
        return products.get(sku);
    }
}
