package br.com.blz.testjava.application.dto.product;

import br.com.blz.testjava.application.dto.inventory.InventoryForm;
import br.com.blz.testjava.application.validator.ValidaObjetoForm;
import br.com.blz.testjava.product.Product;

public class ProductForm extends ValidaObjetoForm {

    private Long sku;

    private String name;

    private InventoryForm inventory;

    public Long getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public InventoryForm getInventory() {
        return inventory;
    }

    public Product converte() throws NoSuchFieldException {
        validaForm(this);
        return new Product(sku, name,inventory);
    }

    @Deprecated
    public ProductForm() {
    }

    public ProductForm(Long sku, String name, InventoryForm inventory) {
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "ProductForm{" +
            "sku='" + sku + '\'' +
            ", name='" + name + '\'' +
            ", inventory=" + inventory +
            '}';
    }
}
