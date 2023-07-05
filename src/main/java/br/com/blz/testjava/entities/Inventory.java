package br.com.blz.testjava.entities;

public class Inventory {

    private int quantity;

    public Inventory() {

    }

    public Inventory(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
