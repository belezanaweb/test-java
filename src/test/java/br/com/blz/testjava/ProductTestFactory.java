package br.com.blz.testjava;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class ProductTestFactory {

    public Product getProduct(){

        List<Warehouse> warehouses = new ArrayList<>();

        warehouses.add(new Warehouse("SP", 12L, "ECOMMERCE"));
        warehouses.add(new Warehouse("MOEMA", 3L, "PHYSICAL_STORE"));

        Inventory inventory = new Inventory(15L, warehouses );

        String name = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";

        return new Product(43264L, name, inventory);

    }

    public Product getProductZeroQuantity(){

        List<Warehouse> warehouses = new ArrayList<>();

        warehouses.add(new Warehouse("SP", 0L, "ECOMMERCE"));
        warehouses.add(new Warehouse("MOEMA", 0L, "PHYSICAL_STORE"));

        Inventory inventory = new Inventory(15L, warehouses );

        String name = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";

        return new Product(43264L, name, inventory);

    }

    public Warehouse getWarehouse(){
        return new Warehouse("CTBA", 10L, "PHYSICAL_STORE");
    }
}
