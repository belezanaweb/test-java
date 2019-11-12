package br.com.blz.testjava.contract.response;

import br.com.blz.testjava.domain.Inventory;

public class ProductResponse {

    private Long sku;
    private String name;
    private InventoryResponse inventory;
    private boolean isMarketable;

    private ProductResponse(Builder builder) {
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

    public InventoryResponse getInventory() {
        return inventory;
    }

    public void setInventory(InventoryResponse inventory) {
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
        return "ProductResponse{" +
            "sku=" + sku +
            ", name='" + name + '\'' +
            ", inventory=" + inventory +
            ", isMarketable=" + isMarketable +
            '}';
    }


    public static final class Builder {
        private Long sku;
        private String name;
        private InventoryResponse inventory;
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

        public Builder withInventory(InventoryResponse val) {
            inventory = val;
            return this;
        }

        public Builder withIsMarketable(boolean val) {
            isMarketable = val;
            return this;
        }

        public ProductResponse build() {
            return new ProductResponse(this);
        }
    }
}
