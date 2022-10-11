package br.com.blz.testjava.model;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.WarehouseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

public class ProductEntity {

    @NotNull
    private Integer sku;

    @NotBlank
    private String name;

    @NotNull
    private InventoryEntity inventory;

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InventoryEntity getInventory() {
        return inventory;
    }

    public void setInventory(InventoryEntity inventory) {
        this.inventory = inventory;
    }

    public ProductDTO buildProductDTO(ProductEntity productEntity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setSku(productEntity.getSku());
        productDTO.setName(productEntity.getName());
        productDTO.setInventory(new InventoryDTO());
        productDTO.getInventory().setWarehouses(productEntity.getInventory().getWarehouses().stream()
            .map(it -> {
                WarehouseDTO warehouseDTO = new WarehouseDTO();
                warehouseDTO.setLocality(it.getLocality());
                warehouseDTO.setQuantity(it.getQuantity());
                warehouseDTO.setType(it.getType());
                return warehouseDTO;

            }).collect(Collectors.toList()));
        productDTO.getInventory().setQuantity(productEntity.getInventory().getWarehouses().stream()
            .filter(it -> it.getQuantity() != null)
            .mapToInt(WarehouseEntity::getQuantity)
            .sum());
        productDTO.setIsMarketable(productDTO.getInventory().getQuantity() > 0);
        return productDTO;
    }
}
