package br.com.blz.testjava.domain;

import org.springframework.data.annotation.Id;
import java.util.Objects;

public class Produto {

    @Id
    private long sku;

    private String name;
    private Inventory inventory;

    public Produto(long sku, String name) {
        this.sku = sku;
        this.name = name;
    }

    public Produto() {}

    public boolean isMarketable() {
        if (inventory == null) {
            return false;
        }
        return inventory.getQuantity() > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return sku == produto.sku;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public long getSku() {
        return sku;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Produto{" +
            "sku=" + sku +
            ", name='" + name + '\'' +
            '}';
    }
}
