package br.com.blz.testjava.service.impl;

import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.exception.ConflictException;
import br.com.blz.testjava.exception.NotFoundException;
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

        Inventory inventory = getCalculatedInventory(product);

        return new Product.Builder()
            .withSku(product.getSku())
            .withInventory(inventory)
            .withName(product.getName())
            .withIsMarketable(inventory.getQuantity() > 0 ? true : false)
            .build();
    }

    @Override
    public Product findBySku(Long sku) {
        Product productDB = repository.findBySku(sku);

        if(productDB == null){
            throw new NotFoundException("Produto não encontrado com sku informado");
        }

        Inventory inventory = getCalculatedInventory(productDB);

        return new Product.Builder()
            .withSku(productDB.getSku())
            .withInventory(inventory)
            .withName(productDB.getName())
            .withIsMarketable(inventory.getQuantity() > 0 ? true : false)
            .build();
    }

    private Inventory getCalculatedInventory(Product product) {
        return new Inventory.Builder()
            .withWarehouses(product.getInventory().getWarehouses())
            .withQuantity(calculateInventoryQuantity(product.getInventory().getWarehouses()))
            .build();
    }

    private Integer calculateInventoryQuantity(List<Warehouse> warehouses) {
        return warehouses.stream()
            .map(w -> w.getQuantity())
            .collect(Collectors.summingInt(Integer::intValue));
    }
}
