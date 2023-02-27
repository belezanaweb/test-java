package br.com.blz.testjava.share;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.model.WarehouseType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductMockHelper {

    public static Product createProductMock(Long spWarehouseQuantity, Long moemaWarehouseQuantity, Long sku) {
        Warehouse spWarehouse = new Warehouse("SP", spWarehouseQuantity, WarehouseType.ECOMMERCE);
        Warehouse moemaWarehouse = new Warehouse("MOEMA", moemaWarehouseQuantity, WarehouseType.PHYSICAL_STORE);

        List<Warehouse> warehouses = new ArrayList<>(2);
        warehouses.add(spWarehouse);
        warehouses.add(moemaWarehouse);

        Inventory inventory = new Inventory(warehouses);

        return new Product(sku, "Product", inventory);
    }

    public static Long getRandomSku() {
        return new Random().nextLong();
    }

}
