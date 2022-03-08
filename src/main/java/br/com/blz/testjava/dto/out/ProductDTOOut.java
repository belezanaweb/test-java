package br.com.blz.testjava.dto.out;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;

public class ProductDTOOut {
    private int sku;
    private String name;
    private InventoryDTOOut inventoryDTOOut;
    private boolean isMarketable = false;

    public ProductDTOOut(Product product) {
        this.setSku(product.getSku());
        this.setName(product.getName());
        this.setInventoryDTOOut(product.getInventory());
        this.setMarketable();
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

    private void setInventoryDTOOut(Inventory inventory) {
        this.inventoryDTOOut = new InventoryDTOOut(inventory);
    }

    public void setMarketable() {
        if (this.inventoryDTOOut.getQuantity() > 0) this.isMarketable = true;
    }
}
