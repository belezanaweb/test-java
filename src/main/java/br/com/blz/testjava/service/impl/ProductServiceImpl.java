package br.com.blz.testjava.service.impl;

import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.exception.ConflictException;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(Product product) {

        if(repository.findBySku(product.getSku()) != null){
            throw new ConflictException("Já existe um produto com o sku informado cadastrado na base");
        }

        repository.save(product);

        Inventory inventory = new Inventory.Builder()
            .withWarehouses(product.getInventory().getWarehouses())
            .withQuantity(calculateInventoryQuantity(product.getInventory().getWarehouses()))
            .build();

        return new Product.Builder()
            .withInventory(inventory)
            .withName(product.getName())
            .withIsMarketable(inventory.getQuantity() > 0 ? true : false)
            .build();
    }

    private Integer calculateInventoryQuantity(List<Warehouse> warehouses) {
        return warehouses.stream()
            .map(w -> w.getQuantity())
            .collect(Collectors.summingInt(Integer::intValue));
    }
}
