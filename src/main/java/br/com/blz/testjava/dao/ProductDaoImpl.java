package br.com.blz.testjava.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.DuplicationException;
import br.com.blz.testjava.exception.NotFoundException;

@Component
@ApplicationScope
public class ProductDaoImpl implements ProductDao {

    private List<Product> products = new ArrayList<>();

    @Override
    public void save(Product product) throws DuplicationException {
        if (products.contains(product)) {
            throw new DuplicationException("Dois produtos são considerados iguais se os seus skus forem iguais");
        }

        products.add(product);
    }

    @Override
    public void update(Product product, Long sku) throws NotFoundException {
        products.remove(findBySku(sku));
        
        product.setSku(sku);
        products.add(product);
    }

    @Override
    public Product findBySku(Long sku) throws NotFoundException {
        return products.stream().filter(product -> product.getSku().equals(sku)).findAny().orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void delete(Long sku) {
        products.removeIf(product -> product.getSku().equals(sku));
    }
}
