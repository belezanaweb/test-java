package br.com.blz.testjava.dto.in;

public class ProductDTOIn {

    private int sku;
    private String name;
    private InventoryDTOIn inventory;

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public InventoryDTOIn getInventoryDTOIn() {
        return inventory;
    }

    public InventoryDTOIn getInventory() {
        return inventory;
    }
}
