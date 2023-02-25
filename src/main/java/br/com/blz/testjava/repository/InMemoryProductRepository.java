package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.Product;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryProductRepository {

    private List<Product> products;

    public InMemoryProductRepository(List<Product> products) {
        this.products = products;
    }

    public Product save(Product product) {
        if(!this.exists(product)) {
            this.products.add(product);
        }

        return product;
    }

    public Optional<Product> findBySku(Long sku) {
        return this.products
            .stream()
            .filter(p -> p.getSku().equals(sku))
            .findFirst();
    }

    public void deleteBySku(Long sku){
        Optional<Product> product = this.findBySku(sku);

        if(product.isPresent()) {
            this.products.remove(product.get());
        }
    }

    public Product update(Product productToUpdate) {
        if(exists(productToUpdate)) {
            this.deleteBySku(productToUpdate.getSku());
            this.save(productToUpdate);
        }

        return productToUpdate;
    }

    public Boolean exists(Product product) {
        return this.products
            .stream()
            .anyMatch(p -> p.getSku().equals(product.getSku()));
    }

    public List<Product> findAll() {
        return this.products.stream().collect(Collectors.toList());
    }

    public int count() {
        return this.products.size();
    }

}
