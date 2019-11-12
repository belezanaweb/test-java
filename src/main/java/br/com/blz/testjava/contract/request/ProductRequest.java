package br.com.blz.testjava.contract.request;

public class ProductRequest {

    private Long sku;
    private String name;
    private InventoryRequest inventory;

    private ProductRequest(Builder builder) {
        setSku(builder.sku);
        setName(builder.name);
        setInventory(builder.inventory);
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

    public InventoryRequest getInventory() {
        return inventory;
    }

    public void setInventory(InventoryRequest inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
            "sku=" + sku +
            ", name='" + name + '\'' +
            ", inventory=" + inventory +
            '}';
    }


    public static final class Builder {
        private Long sku;
        private String name;
        private InventoryRequest inventory;

        public Builder() {
        }

        public Builder sku(Long val) {
            sku = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder inventory(InventoryRequest val) {
            inventory = val;
            return this;
        }

        public ProductRequest build() {
            return new ProductRequest(this);
        }
    }
}
