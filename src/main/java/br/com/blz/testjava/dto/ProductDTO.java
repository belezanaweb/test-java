package br.com.blz.testjava.dto;

import br.com.blz.testjava.model.InventoryEntity;
import br.com.blz.testjava.model.ProductEntity;
import br.com.blz.testjava.model.WarehouseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

public class ProductDTO {

    @NotNull
    private Integer sku;

    @NotNull
    private String name;

    @NotNull
    private InventoryDTO inventory;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isMarketable;

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

    public Boolean getIsMarketable() {
        return isMarketable;
    }

    public void setIsMarketable(Boolean marketable) {
        isMarketable = marketable;
    }

    public InventoryDTO getInventory() {
        return inventory;
    }

    public void setInventory(InventoryDTO inventory) {
        this.inventory = inventory;
    }

    public ProductEntity buildProductEntity(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setSku(productDTO.getSku());
        productEntity.setName(productDTO.getName());
        productEntity.setInventory(new InventoryEntity());
        productEntity.getInventory().setWarehouses(productDTO.getInventory().getWarehouses().stream()
            .map(it-> {
                WarehouseEntity warehouseEntity = new WarehouseEntity();
                warehouseEntity.setLocality(it.getLocality());
                warehouseEntity.setQuantity(it.getQuantity());
                warehouseEntity.setType(it.getType());
                return warehouseEntity;

            }).collect(Collectors.toList()));
        return productEntity;
    }
}
