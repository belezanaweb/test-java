package br.com.blz.testjava.component;

import br.com.blz.testjava.contract.request.ProductRequest;
import br.com.blz.testjava.contract.request.WarehouseRequest;
import br.com.blz.testjava.contract.response.InventoryResponse;
import br.com.blz.testjava.contract.response.ProductResponse;
import br.com.blz.testjava.contract.response.WarehouseResponse;
import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConversorComponent {

    public Product requestToEntityConverter(ProductRequest request){
        List<Warehouse> warehouses = new ArrayList<>();
        Warehouse warehouse = null;

        for (WarehouseRequest wrRequest : request.getInventory().getWarehouses()){
            warehouse = new Warehouse.Builder()
                .withLocality(wrRequest.getLocality())
                .withQuantity(wrRequest.getQuantity())
                .withType(wrRequest.getType())
                .build();

            warehouses.add(warehouse);
        }

        Inventory inventory = new Inventory.Builder()
            .withWarehouses(warehouses)
            .build();

        return new Product.Builder()
            .withSku(request.getSku())
            .withName(request.getName())
            .withInventory(inventory)
            .build();
    }

    public ProductResponse entityToResponseConverter(Product product){
        List<WarehouseResponse> warehouses = new ArrayList<>();
        WarehouseResponse warehouse = null;

        for (Warehouse wr : product.getInventory().getWarehouses()){
            warehouse = new WarehouseResponse.Builder()
                .withLocality(wr.getLocality())
                .withQuantity(wr.getQuantity())
                .withType(wr.getType())
                .build();

            warehouses.add(warehouse);
        }

        InventoryResponse inventory = new InventoryResponse.Builder()
            .withWarehouses(warehouses)
            .withQuantity(product.getInventory().getQuantity())
            .build();

        return new ProductResponse.Builder()
            .withSku(product.getSku())
            .withName(product.getName())
            .withIsMarketable(product.isMarketable())
            .withInventory(inventory)
            .build();
    }
}
