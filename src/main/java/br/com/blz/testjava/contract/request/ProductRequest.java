package br.com.blz.testjava.contract.request;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductRequest {

    @NotNull(message = "Sku cannot be null")
    @Min(value = 1, message = "sku should not be less than 1")
    private Long sku;
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotNull(message = "Inventory cannot be null")
    private InventoryRequest inventory;

    public ProductRequest() {
    }

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

        public Builder withSku(Long val) {
            sku = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withInventory(InventoryRequest val) {
            inventory = val;
            return this;
        }

        public ProductRequest build() {
            return new ProductRequest(this);
        }
    }
}
