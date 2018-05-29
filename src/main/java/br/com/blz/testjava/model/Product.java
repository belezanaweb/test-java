package br.com.blz.testjava.model;

public class Product {

    private Integer sku;
    private String name;
    private Inventory inventory;
    private Boolean isMarketable;

    /**
     * @return the inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * @return the isMarketable
     */
    public Boolean getIsMarketable() {
        return this.inventory.getQuantity() > 0 ? true : false;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the sku
     */
    public Integer getSku() {
        return sku;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(Integer sku) {
        this.sku = sku;
    }

}