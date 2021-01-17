package br.com.blz.testjava.service.impl;

import br.com.blz.testjava.controller.request.InventoryRequest;
import br.com.blz.testjava.controller.request.ProductRequest;
import br.com.blz.testjava.domain.objectvalue.Inventory;
import br.com.blz.testjava.domain.entity.Product;
import br.com.blz.testjava.domain.objectvalue.Warehouse;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Set<Product> findAll() {

        Set<Product> products =  productRepository.findAll();

        products.forEach(this::calculateQuantity);


        return products;
    }

    @Override
    public Product findBySku(Long sku) {

        Product product = productRepository.findBySku(sku);
        calculateQuantity(product);

        return product;
    }

    @Override
    public void create(ProductRequest productRequest) {

        InventoryRequest inventoryRequest = productRequest.getInventory();
        Inventory inventory = new Inventory(inventoryRequest.getWarehouses());

        Product product = new Product(
            productRequest.getSku(),
            productRequest.getName(),
            inventory
        );

        productRepository.save(product);
    }

    @Override
    public Product update(ProductRequest productRequest) {

        InventoryRequest inventoryRequest = productRequest.getInventory();
        Inventory inventory = new Inventory(inventoryRequest.getWarehouses());

        productRepository.update(
            new Product(
                productRequest.getSku(),
                productRequest.getName(),
                inventory
            )
        );

        return findBySku(productRequest.getSku());
    }

    @Override
    public void delete(Long sku) {
        productRepository.delete(sku);
    }

    protected void defineMarketable(Product product) {
        if(product.getInventory().getQuantity() > 0) {
            product.setMarketable(true);
        }
    }

    protected void calculateQuantity(Product product) {

        Inventory inventory = product.getInventory();
        List<Warehouse> warehouses = inventory.getWarehouses();

        inventory.setQuantity(
            warehouses
                .stream()
                .mapToInt(Warehouse::getQuantity)
                .sum()
        );

        defineMarketable(product);
    }
}
