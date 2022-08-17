package br.com.blz.testjava.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import br.com.blz.testjava.model.ProductModel;

@Repository
public class ProductRepositoryImpl implements IProductRepository {

    private Map<Integer, ProductModel> products = new ConcurrentHashMap<>();

    @Override
    public void createProduct(final ProductModel productModel) {
    	products.put(productModel.getSku(), productModel);
    }

    @Override
    public void updateProduct(final ProductModel productModel) {
        products.put(productModel.getSku(), productModel);
    }

    @Override
    public void deleteProduct(final Integer sku){
        products.remove(sku);
    }
    
    @Override
    public ProductModel findProductBySku(final Integer sku){
        return products.get(sku);
    }
}
