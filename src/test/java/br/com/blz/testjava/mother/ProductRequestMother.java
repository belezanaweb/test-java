package br.com.blz.testjava.mother;

import static java.util.Arrays.asList;

import br.com.blz.testjava.controller.request.InventoryRequest;
import br.com.blz.testjava.controller.request.ProductRequest;
import br.com.blz.testjava.controller.request.WarehouseRequest;

public class ProductRequestMother {
    
    private ProductRequestMother() {
    }
    
    public static ProductRequest getProductRequest(long sku) {
        ProductRequest product = new ProductRequest();
        product.setSku(sku);
        product.setName("Teste");
        product.setInventory(new InventoryRequest());
        
        WarehouseRequest warehouse1 = new WarehouseRequest();
        warehouse1.setType("Dummy Type 1");
        warehouse1.setQuantity(2);
        warehouse1.setLocality("SP");
        
        WarehouseRequest warehouse2 = new WarehouseRequest();
        warehouse2.setType("Dummy Type 2");
        warehouse2.setQuantity(2);
        warehouse2.setLocality("RJ");
        
        product.getInventory().setWarehouses(asList(warehouse1, warehouse2));
        return product;
    }
}
