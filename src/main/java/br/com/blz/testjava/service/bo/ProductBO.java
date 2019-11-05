package br.com.blz.testjava.service.bo;

import br.com.blz.testjava.controller.data.ProductAttributes;
import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class ProductBO {

    public static Product parsePojoToEntity(ProductAttributes attr) {

        Product product = new Product();

        product.setSku(attr.getSku());
        product.setName(attr.getName());
        product.setIsMarketable(false);

        Inventory inventory = new Inventory();

        List<Warehouse> warehouses = new ArrayList<>();

        attr.getInventory().getWarehouses().stream().forEach(
            f-> warehouses.add(new Warehouse(f.getLocality(), f.getQuantity(), f.getType()))
        );

        inventory.setQuantity(0);
        inventory.setWarehouses(warehouses);

        product.setInventory(inventory);

        return product;
    }

}
