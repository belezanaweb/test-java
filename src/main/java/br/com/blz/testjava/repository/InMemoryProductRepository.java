package br.com.blz.testjava.repository;

import br.com.blz.testjava.exceptions.DuplicatedProductException;
import br.com.blz.testjava.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryProductRepository implements Repository {

    private List<Product> products;

    public InMemoryProductRepository(List<Product> products) {
        this.products = products;
    }

    public Product save(Product product) {
        if(this.exists(product)) {
            throw new DuplicatedProductException("Duplicated SKU");
        }

        this.products.add(product);

        return product;
    }

    public Optional<Product> findBySku(Long sku) {
        return this.products
            .stream()
            .filter(p -> p.getSku().equals(sku))
            .findFirst();
    }

    public void deleteBySku(Long sku) {
        Optional<Product> product = this.findBySku(sku);
        product.ifPresent(value -> this.products.remove(value));
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
