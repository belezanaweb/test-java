package br.com.blz.testjava;

public class Product {

    private int sku;
    private String name;
    private boolean isMarketable;
    private Inventory inventory;

    public Product (ProductDTOIn productDTOIn){
        this.sku = productDTOIn.getSku();
        this.name = productDTOIn.getName();
        this.inventory = new Inventory(productDTOIn.getInventoryDTOIn());

    }

}
