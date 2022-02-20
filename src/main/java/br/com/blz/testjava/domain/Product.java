package br.com.blz.testjava.domain;

import br.com.blz.testjava.data.dto.ProductDTO;
import br.com.blz.testjava.data.response.InventoryResponse;
import br.com.blz.testjava.data.response.PointOfServiceTypeResponse;
import br.com.blz.testjava.data.response.ProductResponse;
import br.com.blz.testjava.data.response.WarehouseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.blz.testjava.utils.IdGeneratorUtils.generateId;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Integer id;
    private String sku;
    private String name;
    private Inventory inventory;
    private boolean isMarketable;

    public Product(ProductDTO productDTO) {

        this.id = generateId();
        this.sku = productDTO.getSku();
        this.name = productDTO.getName();

        final List<Warehouse> warehouses = productDTO.getInventory().getWarehouses()
            .stream().map(w -> {
                Warehouse warehouse = new Warehouse();
                warehouse.setLocality(w.getLocality());
                warehouse.setQuantity(w.getQuantity());
                warehouse.setType(PointOfServiceType.valueOf(w.getType().name()));
                warehouse.setId(generateId());

                return warehouse;
            }).collect(Collectors.toUnmodifiableList());

        Inventory inventory = new Inventory();
        inventory.setId(generateId());
        inventory.setWarehouses(warehouses);
        inventory.setQuantity(warehouses.size());
        this.inventory = inventory;

    }

    public ProductResponse toResponse() {


        final List<WarehouseResponse> warehouseResponse = inventory.getWarehouses()
            .stream().map(w -> {
                WarehouseResponse warehouse = new WarehouseResponse();
                warehouse.setLocality(w.getLocality());
                warehouse.setQuantity(warehouse.getQuantity());
                warehouse.setType(PointOfServiceTypeResponse.valueOf(w.getType().name()));
                return warehouse;
            }).collect(Collectors.toUnmodifiableList());

        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setQuantity(warehouseResponse.size());
        inventoryResponse.setWarehouses(warehouseResponse);


        return ProductResponse.builder()
            .sku(sku)
            .name(name)
            .inventory(inventoryResponse)
            .isMarketable(inventory.getQuantity() > 0)
            .build();

    }

}
