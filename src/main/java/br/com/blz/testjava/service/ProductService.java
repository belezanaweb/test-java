package br.com.blz.testjava.service;

import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.form.ProductForm;
import br.com.blz.testjava.form.WarehouseForm;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void create(ProductForm form) throws ProductAlreadyExistsException {
        if (productRepository.existsProduct(form.getSku())) {
            throw new ProductAlreadyExistsException();
        }

        Product product = new Product();
        product.setSku(form.getSku());
        product.setName(form.getName());
        product.setInventory(calculteInventory(form));
        product.setIsMarketable(isMarketable(calculteInventory(form)));

        productRepository.save(product);
    }

    public Product find(Long sku) throws ProductNotFoundException {
        if (!productRepository.existsProduct(sku)) {
            throw new ProductNotFoundException();
        }

        Product product = productRepository.findBySku(sku);
        product.setInventory(calculteInventory(product));
        product.setIsMarketable(isMarketable(product.getInventory()));

        return product;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void update(ProductForm form) throws ProductNotFoundException {
        if (!productRepository.existsProduct(form.getSku())) {
            throw new ProductNotFoundException();
        }

        Product product = new Product();
        product.setSku(form.getSku());
        product.setName(form.getName());
        product.setInventory(calculteInventory(form));
        product.setIsMarketable(isMarketable(calculteInventory(form)));

        productRepository.update(product);
    }

    public void delete(Long sku) throws ProductNotFoundException {
        if (!productRepository.existsProduct(sku)) {
            throw new ProductNotFoundException();
        }

        productRepository.delete(sku);
    }

    private Inventory calculteInventory(ProductForm form) {
        List<Warehouse> warehouses = new ArrayList();
        Long quantity = Long.valueOf(0);

        for (WarehouseForm warehouse : form.getWarehouses()) {
            quantity += warehouse.getQuantity();
            Warehouse wh = new Warehouse();
            wh.setLocality(warehouse.getLocality());
            wh.setQuantity(warehouse.getQuantity());
            wh.setType(warehouse.getType());

            warehouses.add(wh);
        }

        Inventory inventory = new Inventory();
        inventory.setQuantity(quantity);
        inventory.setWarehouses(warehouses);

        return inventory;
    }

    private Inventory calculteInventory(Product product) {

        Long quantity = Long.valueOf(0);

        for (Warehouse warehouse : product.getInventory().getWarehouses()) {
            quantity += warehouse.getQuantity();
        }

        Inventory inventory = new Inventory();
        inventory.setQuantity(quantity);
        inventory.setWarehouses(product.getInventory().getWarehouses());

        return inventory;
    }

    private Boolean isMarketable(@NotNull Inventory inventory) {
        return inventory.getQuantity() > 0;
    }

}
