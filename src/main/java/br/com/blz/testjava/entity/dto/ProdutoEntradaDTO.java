package br.com.blz.testjava.entity.dto;


public class ProdutoEntradaDTO {

    private Long sku;
    private String name;
    private InventoryEntrada inventory;

    public ProdutoEntradaDTO(Long sku, String name, InventoryEntrada inventory) {
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
    }

    public ProdutoEntradaDTO() {
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

    public InventoryEntrada getInventory() {
        return this.inventory;
    }

    public void setInventory(InventoryEntrada inventory) {
        this.inventory = inventory;
    }
}
