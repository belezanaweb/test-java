package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Scope("singleton")
@Repository
public class ProductRepository {
    List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
