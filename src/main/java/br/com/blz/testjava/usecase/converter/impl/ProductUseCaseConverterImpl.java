package br.com.blz.testjava.usecase.converter.impl;

import br.com.blz.testjava.gateway.documents.InventoryDocument;
import br.com.blz.testjava.gateway.documents.ProductDocument;
import br.com.blz.testjava.gateway.documents.WarehouseDocument;
import br.com.blz.testjava.gateway.documents.WarehouseTypeDocument;
import br.com.blz.testjava.usecase.converter.ProductUseCaseConverter;
import br.com.blz.testjava.usecase.data.Inventory;
import br.com.blz.testjava.usecase.data.Product;
import br.com.blz.testjava.usecase.data.Warehouse;
import br.com.blz.testjava.usecase.data.WarehouseType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;
import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class ProductUseCaseConverterImpl implements ProductUseCaseConverter {

    @Override
    public ProductDocument toRequest(Product product) {
        if(nonNull(product)){

            ProductDocument document = new ProductDocument();
            InventoryDocument inventoryDocument = new InventoryDocument();
            List<WarehouseDocument> warehouseDocuments = new ArrayList<>();

            inventoryDocument.setWarehouses(warehouseDocuments);
            document.setInventory(inventoryDocument);

            copyProperties(product, document);
            //copyProperties(product.getInventory(), inventoryDocument);

            product.getInventory().getWarehouses().forEach(warehouse ->{
                WarehouseDocument warehouseDocument = new WarehouseDocument();
                copyProperties(warehouse, warehouseDocument);
                warehouseDocument.setType(WarehouseTypeDocument.valueOf(warehouse.getType().name()));
                warehouseDocuments.add(warehouseDocument);

            });

            return document;
        }
        return null;
    }

    @Override
    public Product toResponse(ProductDocument productDocument) {
        if(nonNull(productDocument)){

            Product product = new Product();
            Inventory inventory = new Inventory();
            List<Warehouse> warehouses = new ArrayList<>();

            inventory.setWarehouses(warehouses);
            product.setInventory(inventory);

            copyProperties(productDocument, product);
            copyProperties(productDocument.getInventory(), inventory, "warehouses");

            productDocument.getInventory().getWarehouses().forEach(warehouseDocument -> {
                Warehouse warehouse = new Warehouse();
                copyProperties(warehouseDocument, warehouse);
                warehouse.setType(WarehouseType.valueOf(warehouseDocument.getType().name()));
                warehouses.add(warehouse);
            });

            return product;
        }
        return null;
    }
}
