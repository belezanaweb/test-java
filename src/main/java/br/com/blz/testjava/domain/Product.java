package br.com.blz.testjava.domain;

import br.com.blz.testjava.contract.request.InventoryRequest;

public class Product {

    private Long sku;
    private String name;
    private Inventory inventory;
    private boolean isMarketable;

    private Product(Builder builder) {
        setSku(builder.sku);
        setName(builder.name);
        setInventory(builder.inventory);
        setMarketable(builder.isMarketable);
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public boolean isMarketable() {
        return isMarketable;
    }

    public void setMarketable(boolean marketable) {
        isMarketable = marketable;
    }

    @Override
    public String toString() {
        return "Product{" +
            "sku=" + sku +
            ", name='" + name + '\'' +
            ", inventory=" + inventory +
            ", isMarketable=" + isMarketable +
            '}';
    }


    public static final class Builder {
        private Long sku;
        private String name;
        private Inventory inventory;
        private boolean isMarketable;

        public Builder() {
        }

        public Builder withSku(Long val) {
            sku = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withInventory(Inventory val) {
            inventory = val;
            return this;
        }

        public Builder withIsMarketable(boolean val) {
            isMarketable = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
