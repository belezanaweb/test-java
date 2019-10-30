package br.com.blz.testjava;

import br.com.blz.testjava.model.Inventary;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeed implements ApplicationRunner {
    private ProductService service;

    @Autowired
    public DataSeed(ProductService service) {
        this.service = service;
    }

    public void run(ApplicationArguments args) {
        this.service.save(DataSeed.createProduct());
    }

    public static Product createProduct() {
        Product product = new Product();
        product.setSku(43264);
        product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");

        List<Warehouse> warehouses = new ArrayList<>();
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("SP");
        warehouse.setQuantity(12);
        warehouse.setType("ECOMMERCE");

        warehouses.add(warehouse);

        Warehouse anotherWarehouse = new Warehouse();
        anotherWarehouse.setLocality("MOEMA");
        anotherWarehouse.setQuantity(3);
        anotherWarehouse.setType("PHYSICAL_STORE");

        warehouses.add(anotherWarehouse);

        Inventary inventary = new Inventary();
        inventary.setWarehouses(warehouses);

        product.setInventary(inventary);

        return product;
    }
}
