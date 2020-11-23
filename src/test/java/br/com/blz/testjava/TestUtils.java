package br.com.blz.testjava;

import br.com.blz.testjava.dto.InventoryDto;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.dto.WarehouseDto;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;

import java.util.List;

public abstract class TestUtils {

    public static String getProductPayload() {
        return "{" +
            "\"sku\": 43264," +
            "\"name\": \"L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g\"," +
            "\"inventory\": {" +
            "  \"warehouses\": [" +
            "       {" +
            "       \"locality\": \"SP\"," +
            "       \"quantity\": 12," +
            "       \"type\": \"ECOMMERCE\"" +
            "       }," +
            "       {" +
            "       \"locality\": \"MOEMA\"," +
            "       \"quantity\": 3," +
            "       \"type\": \"PHYSICAL_STORE\"" +
            "       }" +
            "   ]" +
            "  }" +
            "}";
    }

    public static ProductDto getProductDto(Long sku, String name) {
        var dto = new ProductDto();
        dto.setId(sku);
        dto.setName(name);
        dto.setInventory(new InventoryDto());
        dto.getInventory().setWarehouses(List.of(new WarehouseDto()));
        return dto;
    }

    public static Product getProduct(Long sku, String name, int quantity1, int quantity2) {
        var entity = new Product();
        entity.setId(sku);
        entity.setName(name);
        entity.setInventory(new Inventory());
        entity.getInventory().setWarehouses(List.of(new Warehouse(), new Warehouse()));
        entity.getInventory().getWarehouses().get(0).setQuantity(quantity1);
        entity.getInventory().getWarehouses().get(1).setQuantity(quantity2);
        return entity;
    }

}
