package br.com.blz.testjava.dto.out;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;

public class ProductDTOOut {
    private int sku;
    private String name;
    private InventoryDTOOut inventory;
    private boolean isMarketable = false;

    public ProductDTOOut(Product product) {
        this.setSku(product.getSku());
        this.setName(product.getName());
        this.setInventory(product.getInventory());
        this.setMarketable();
    }

    public InventoryDTOOut getInventory() {
        return inventory;
    }

    public int getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public boolean isMarketable() {
        return isMarketable;
    }

    private void setSku(int sku) {
        this.sku = sku;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setInventory(Inventory inventory) {
        this.inventory = new InventoryDTOOut(inventory);
    }

    public void setMarketable() {
        if (this.inventory.getQuantity() > 0) this.isMarketable = true;
    }
}
