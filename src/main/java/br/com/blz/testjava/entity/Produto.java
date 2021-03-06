package br.com.blz.testjava.entity;

import java.util.Objects;

public class Produto {

    private Long sku;
    private String name;
    private int quantity;

    public Produto(Long sku, String name, int quantity) {
        this.sku = sku;
        this.name = name;
        this.quantity = quantity;
    }

    public Produto() {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Produto{" +
            "sku=" + sku +
            ", name='" + name + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object outroProduto) {
        Produto produto = (Produto) outroProduto;
        return Objects.equals(sku, produto.sku) &&
            Objects.equals(name, produto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }
}
