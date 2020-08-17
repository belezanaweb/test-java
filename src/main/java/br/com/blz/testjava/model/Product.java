package br.com.blz.testjava.model;


import br.com.blz.testjava.domain.request.ProductCreateRequest;
import br.com.blz.testjava.domain.request.ProductUpdateRequest;

public class Product {

    private Long sku;
    private String name;
    private Inventory inventory;

    public Product() {
    }

    public Product(Long sku, ProductUpdateRequest productUpdateRequest) {
        setSku(sku);
        setName(productUpdateRequest.getName());
        setInventory(new Inventory(productUpdateRequest.getInventory()));
    }

    public Product(ProductCreateRequest productCreateRequest) {
        this(productCreateRequest.getSku(), productCreateRequest);
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
}
