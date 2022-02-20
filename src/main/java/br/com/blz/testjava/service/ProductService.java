package br.com.blz.testjava.service;

import br.com.blz.testjava.data.dto.ProductDTO;
import br.com.blz.testjava.data.response.ProductResponse;
import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.PointOfServiceType;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.exception.BoticarioNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.blz.testjava.utils.MemoryUtils.*;

@Service
@Slf4j
public class ProductService {

    public ProductResponse createProduct(ProductDTO productDTO) {
        final Product product = new Product(productDTO);
        persistProduct(product);
        return product.toResponse();
    }

    public ProductResponse updateProduct(String sku, ProductDTO productDTO) {

        final Product productPersisted = findBySku(sku);

        if (productPersisted == null) {
            log.error("product not found " + sku);
            throw new BoticarioNotFoundException("Product not found: [ " + sku + " ] ");
        }

        List<Warehouse> warehouseList = productDTO.getInventory().getWarehouses()
            .stream().map(w -> {
                Warehouse warehouse = new Warehouse();
                warehouse.setQuantity(w.getQuantity());
                warehouse.setType(PointOfServiceType.ECOMMERCE);
                warehouse.setLocality(w.getLocality());
                return warehouse;
            }).collect(Collectors.toUnmodifiableList());

        final Inventory inventory = new Inventory();
        inventory.setWarehouses(warehouseList);
        inventory.setQuantity(productDTO.getInventory().getWarehouses().size());


        productPersisted.setInventory(inventory);
        productPersisted.setName(productDTO.getName());

        updateBySku(productPersisted);

        return productPersisted.toResponse();
    }


    public ProductResponse findProduct(String sku) {
        final Product product = findBySku(sku);

        if (product == null) {
            log.error("Product not found " + sku);
            throw new BoticarioNotFoundException("Product not found: [ " + sku + " ] ");
        }

        return product.toResponse();
    }

    public void deleteProduct(String sku) {
        deleteBySku(sku);
    }
}
