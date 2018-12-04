package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.exception.ProductNotExist;
import br.com.blz.testjava.exception.ProductSkuAlreadyExist;
import br.com.blz.testjava.repository.ProductRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseService warehouseService;

    private void save(Product product) {
        productRepository.save(product);
        product.getInventory()
                .getWarehouses()
                .forEach(werehouse -> {
                    werehouse.setProductSku(product.getSku());
                    warehouseService.save(werehouse);
                });
    }

    public void create(Product product) {

        if (Objects.isNull(product.getSku())) {
            throw new IllegalArgumentException();
        }

        Product productFound = productRepository.findOne(product.getSku());
        if (Objects.nonNull(productFound)) {
            throw new ProductSkuAlreadyExist("Product Already Exist");
        }
        save(product);
    }

    public void update(Product product) {
        Product productFound = productRepository.findOne(product.getSku());
        if (Objects.isNull(productFound)) {
            throw new ProductNotExist("Product not exist in database");
        }
        warehouseService.deleteByProductSku(product.getSku());
        save(product);
    }

    public Product getBySku(Long sku) {
        Product product = productRepository.findOne(sku);

        Inventory inventory = new Inventory();
        List<Warehouse> warehouses = warehouseService.getByProductSku(product.getSku());
        inventory.setWarehouses(warehouses);

        product.setInventory(inventory);
        return product;
    }

    public void deleteBySku(Long id) {
        productRepository.delete(id);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
