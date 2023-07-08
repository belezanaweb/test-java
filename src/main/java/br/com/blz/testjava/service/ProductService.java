package br.com.blz.testjava.service;


import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(Product product) {
        calculateInventoryQuantity(product);
        calculateIsMarketable(product);
        productRepository.save(product);
    }

    public void updateProduct(Integer sku, Product updatedProduct) {
        Product existingProduct = getProductBySku(sku);
        if (existingProduct != null) {
            // Atualizar os campos do produto existente com os valores do produto atualizado
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setInventory(updatedProduct.getInventory());

            calculateInventoryQuantity(existingProduct);
            calculateIsMarketable(existingProduct);

            productRepository.save(existingProduct);
        }
    }

    public Product getProductBySku(Integer sku) {
        return productRepository.findById(sku).orElse(null);
    }

    public void deleteProductBySku(Integer sku) {
        productRepository.deleteById(sku);
    }

    private void calculateInventoryQuantity(Product product) {
        Inventory inventory = product.getInventory();
        List<Warehouse> warehouses = inventory.getWarehouses();
        int totalQuantity = warehouses.stream().mapToInt(Warehouse::getQuantity).sum();
        inventory.setQuantity(totalQuantity);
    }

    private void calculateIsMarketable(Product product) {
        Inventory inventory = product.getInventory();
        int totalQuantity = inventory.getQuantity();
        product.setMarketable(totalQuantity > 0);
    }
}
