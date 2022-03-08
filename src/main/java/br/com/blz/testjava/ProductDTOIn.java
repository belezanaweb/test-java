package br.com.blz.testjava;

public class ProductDTOIn {

    private int sku;
    private String name;
    private InventoryDTOIn inventoryDTOIn;

    public int getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public InventoryDTOIn getInventoryDTOIn() {
        return inventoryDTOIn;
    }
}
