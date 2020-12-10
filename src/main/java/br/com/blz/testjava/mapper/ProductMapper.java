package br.com.blz.testjava.mapper;

import br.com.blz.testjava.controller.request.ProductRequest;
import br.com.blz.testjava.controller.request.WarehouseRequest;
import br.com.blz.testjava.controller.response.InventoryResponse;
import br.com.blz.testjava.controller.response.ProductResponse;
import br.com.blz.testjava.controller.response.WarehouseResponse;
import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductMapper {

    public static Product toDomain(ProductRequest request) {
        List<Warehouse> warehouses = new ArrayList<>();
        for (WarehouseRequest wreq : Optional.ofNullable(request.getInventory().getWarehouses()).orElse(Collections.emptyList())) {
            Warehouse wdomain = new Warehouse();
            wdomain.setType(Warehouse.Type.valueOf(wreq.getType().toString()));
            wdomain.setQuantity(wreq.getQuantity());
            wdomain.setLocality(wreq.getLocality());
            warehouses.add(wdomain);
        }
        Inventory i = new Inventory();
        i.setWarehouses(warehouses);
        Product p = new Product();
        p.setName(request.getName());
        p.setSku(request.getSku());
        p.setInventory(i);

        return p;
    }

    public static ProductResponse toResponse(Product product) {
        List<WarehouseResponse> warehouses = new ArrayList<>();
        for (Warehouse wr : Optional.ofNullable(product.getInventory().getWarehouses()).orElse(Collections.emptyList())) {
            WarehouseResponse wresp = new WarehouseResponse();
            wresp.setType(WarehouseResponse.Type.valueOf(wr.getType().toString()));
            wresp.setQuantity(wr.getQuantity());
            wresp.setLocality(wr.getLocality());
            warehouses.add(wresp);
        }

        InventoryResponse i = new InventoryResponse();
        i.setQuantity(product.getInventory().getQuantity());
        i.setWarehouses(warehouses);

        ProductResponse p = new ProductResponse();
        p.setName(product.getName());
        p.setSku(product.getSku());
        p.setInventory(i);
        p.setId(product.getId());
        p.setMarketable(product.isMarketable());

        return p;
    }
}
