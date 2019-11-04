package br.com.blz.testjava.http.converter.impl;

import br.com.blz.testjava.http.converter.ProductHttpConverter;
import br.com.blz.testjava.http.data.request.ProductRequest;
import br.com.blz.testjava.http.data.response.InventoryResponse;
import br.com.blz.testjava.http.data.response.ProductResponse;
import br.com.blz.testjava.http.data.response.WarehouseResponse;
import br.com.blz.testjava.http.data.response.WarehouseTypeResponse;
import br.com.blz.testjava.usecase.data.Inventory;
import br.com.blz.testjava.usecase.data.Product;
import br.com.blz.testjava.usecase.data.Warehouse;
import br.com.blz.testjava.usecase.data.WarehouseType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class ProductHttpConverterImpl implements ProductHttpConverter {

    @Override
    public Product toRequest(ProductRequest request) {
        if(nonNull(request)){
            Product product = new Product();
            Inventory inventory = new Inventory();
            List<Warehouse> warehouses = new ArrayList<>();

            inventory.setWarehouses(warehouses);
            product.setInventory(inventory);

            copyProperties(request, product);
            //copyProperties(request.getInventory(), inventory);

            request.getInventory().getWarehouses().forEach(warehouseRequest -> {
                Warehouse warehouse = new Warehouse();
                copyProperties(warehouseRequest, warehouse);
                warehouse.setType(WarehouseType.valueOf(warehouseRequest.getType().name()));
                warehouses.add(warehouse);
            });



            return product;
        }

        return null;
    }

    @Override
    public ProductResponse toResponse(Product product) {

        if(nonNull(product)){
            ProductResponse response = new ProductResponse();
            InventoryResponse inventoryResponse = new InventoryResponse();
            List<WarehouseResponse> warehouseResponses = new ArrayList<>();
            inventoryResponse.setWarehouses(warehouseResponses);
            response.setInventory(inventoryResponse);

            copyProperties(product, response);
            copyProperties(product.getInventory(), inventoryResponse, "warehouses");

            product.getInventory().getWarehouses().forEach(warehouse -> {
                WarehouseResponse warehouseResponse = new WarehouseResponse();
                copyProperties(warehouse, warehouseResponse);
                warehouseResponse.setType(WarehouseTypeResponse.valueOf(warehouse.getType().name()));
                warehouseResponses.add(warehouseResponse);

            });

            return response;
        }

        return null;
    }

}
