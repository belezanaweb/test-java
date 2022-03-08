package br.com.blz.testjava;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements IProductRepository {

    List<Product> products =  new ArrayList<>();

    @Override
    public void save(ProductDTOIn productDTOIn) {

    }

    @Override
    public ProductDTOOut edit(ProductDTOIn productDTOIn) {
        return null;
    }

    @Override
    public ProductDTOOut get(int productSku) {
        return null;
    }

    @Override
    public void delete(int productSku) {

    }
}
