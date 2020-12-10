package br.com.blz.testjava.mock;

import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;

import java.util.Arrays;

public class ProductMock {
    public static Product create(String name, long sku, int quantity, String locality, Warehouse.Type type){
        Product p = new Product();
        Inventory i = new Inventory();
        Warehouse w = new Warehouse();
        w.setQuantity(quantity);
        w.setLocality(locality);
        w.setType(type);
        i.setWarehouses(Arrays.asList(w));
        p.setInventory(i);
        p.setName(name);
        p.setSku(sku);
        return p;
    }
}
