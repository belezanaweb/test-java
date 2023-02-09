package br.com.blz.testjava.testUtils;

import br.com.blz.testjava.api.enums.TypeWarehouse;
import br.com.blz.testjava.api.inventory.entity.InventoryEntity;
import br.com.blz.testjava.api.product.controller.domain.ProductRequest;
import br.com.blz.testjava.api.product.controller.domain.ProductResponse;
import br.com.blz.testjava.api.product.dto.InventoryDto;
import br.com.blz.testjava.api.product.dto.WarehousesDto;
import br.com.blz.testjava.api.product.entity.ProductEntity;
import br.com.blz.testjava.api.werehouse.entity.WarehouseEntity;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

  public static ProductRequest getProductRequest() {
    ProductRequest productResponse = new ProductRequest();
    productResponse.setInventory(getInventory());
    productResponse.setName("product");

    return productResponse;
  }

  public static ProductResponse getProductResponse() {
    ProductResponse productResponse = new ProductResponse();
    productResponse.setInventory(getInventory());
    productResponse.setName("product");

    return productResponse;
  }

  public static InventoryDto getInventory() {
    InventoryDto inventoryDto = new InventoryDto();
    inventoryDto.setQuantity(10);
    inventoryDto.setWarehouses(getWarehouses());
    return inventoryDto;
  }

  public static List<WarehousesDto> getWarehouses() {
    List<WarehousesDto> warehousesDtos = new ArrayList<>();
    WarehousesDto warehouses = new WarehousesDto();
    warehouses.setLocality("SP");
    warehouses.setQuantity(2);
    warehouses.setType(TypeWarehouse.ECOMMERCE);
    warehousesDtos.add(warehouses);
    return warehousesDtos;
  }

  public static ProductEntity getProductEntity() {
    ProductEntity productResponse = new ProductEntity();
    productResponse.setInventory(getInventoryEntity());
    productResponse.setName("product");
    productResponse.setSku(1);
    productResponse.setId(1);

    return productResponse;
  }

  public static InventoryEntity getInventoryEntity() {
    InventoryEntity inventoryDto = new InventoryEntity();
    inventoryDto.setId(10);
    inventoryDto.setWerehouses(getWarehouseEntity());
    return inventoryDto;
  }

  public static List<WarehouseEntity> getWarehouseEntity() {
    List<WarehouseEntity> warehousesDtos = new ArrayList<>();
    WarehouseEntity warehouses = new WarehouseEntity();
    warehouses.setId(1L);
    warehouses.setLocality("SP");
    warehouses.setQuantity(2);
    warehouses.setType(TypeWarehouse.ECOMMERCE);
    warehousesDtos.add(warehouses);
    return warehousesDtos;
  }
}
