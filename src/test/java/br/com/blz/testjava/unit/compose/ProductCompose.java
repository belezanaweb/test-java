package br.com.blz.testjava.unit.compose;

import br.com.blz.testjava.entities.Inventory;
import br.com.blz.testjava.entities.Product;

public class ProductCompose {

    private static final Inventory INVENTORY_DEFAULT = InventoryCompose.getInventoryDefault();
    private static final Inventory INVENTORY_RANDOM = InventoryCompose.getInventoryRandom();

    public static Product getProductDefault() {
        return Product.builder()
            .sku("43264")
            .name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")
            .inventory(INVENTORY_DEFAULT)
            .build();
    }

    public static Product getProductRandom() {
        return Product.builder()
            .sku(Utils.getNumeroAleatorio().toString())
            .name(Utils.getPalavraAleatoria())
            .inventory(INVENTORY_RANDOM)
            .build();
    }

}
