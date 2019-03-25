package br.com.blz.testjava;

import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.exception.BlzException;
import br.com.blz.testjava.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @Test
    public void save() {

        boolean success = false;

        try {
            Product product = this.createProduct();
            productService.save(product);
            success = true;

        } catch (Exception e) {
            Assert.assertFalse(success);
        }

        Assert.assertTrue(success);
    }

    @Test
    public void saveDuplicated() throws BlzException {

        try {
            Product product = this.createProduct();
            productService.save(product);

        } catch (Exception e) {
            Assert.assertTrue(e instanceof BlzException);
        }
    }

    private final Product createProduct() {

        Product product = new Product();
        product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
        product.setSku(43264);
        product.setInventory(this.createInventory());

        return product;
    }

    private final Inventory createInventory() {

        Inventory inventory = new Inventory();
        inventory.setWarehouses(this.createWarehouses());

        return inventory;
    }

    private final List<Warehouse> createWarehouses() {

        List<Warehouse> warehouses = new ArrayList<>();

        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("SP");
        warehouse.setQuantity(Integer.valueOf(12));
        warehouse.setType("ECOMMERCE");

        warehouses.add(warehouse);

        warehouse = new Warehouse();
        warehouse.setLocality("MOEMA");
        warehouse.setQuantity(Integer.valueOf(3));
        warehouse.setType("PHYSICAL_STORE");

        warehouses.add(warehouse);

        return warehouses;

    }
}
