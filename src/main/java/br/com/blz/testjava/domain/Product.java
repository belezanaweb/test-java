package br.com.blz.testjava.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.blz.testjava.validation.group.Insertion;

public class Product {

    @NotNull(message = "Sku é obrigatório", groups = Insertion.class)
    private Long sku;

    @NotBlank(message = "Name é obrigatório")
    private String name;

    @Valid
    @NotNull(message = "Inventory é obrigatório")
    private Inventory inventory;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Product)) {
            return false;
        }

        Product other = (Product) obj;
        if (sku == null) {
            if (other.sku != null) {
                return false;
            }
        } else if (!sku.equals(other.sku)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sku == null) ? 0 : sku.hashCode());
        return result;
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

    public boolean getIsMarketable(){
        return this.inventory.getQuantity() > 0;
    }
}
